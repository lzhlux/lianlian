package cn.com.tichain.gateway.interceptor;

import cn.com.tichain.gateway.bean.ReplayAttacks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


@Component
public class ReplayAttacksInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equals(request.getMethod())) {
            return true;
        }
        String query = request.getQueryString();
        ReplayAttacks ra = analyzeQueryToRA(query);
        checkSign(ra, request.getHeader("Authorization"));
        checkTimestamp(ra.getTimestamp());
        checkNonce(ra.getNonce());
        return false;
    }

    private void checkSign(ReplayAttacks ra, String authorization) throws Exception {
        if (authorization == null) {
            throw new Exception("无Authorization");
        }
        String[] split = authorization.split("\\.");
        if (split.length != 3) {
            throw new Exception("Authorization 错误");
        }
        String before = split[2] + ra.getTimestamp() + ra.getNonce();
        String sign = DigestUtils.md5DigestAsHex(before.getBytes());
        if (!sign.equals(ra.getSign())) {
            throw new Exception("sign签名不正确");
        }
    }

    private void checkNonce(long nonce) throws Exception {
        String has = stringRedisTemplate.opsForValue().get("" + nonce);
        if (has != null) {
            throw new Exception("无效的请求");
        }
        stringRedisTemplate.opsForValue().set("" + nonce, "",1, TimeUnit.MINUTES);
    }

    // 检查时间戳
    private void checkTimestamp(long timestamp) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -5);
        long min = cal.getTimeInMillis();
        if (timestamp < min) {
            throw new Exception("该请求已失效");
        }
    }

    private ReplayAttacks analyzeQueryToRA(String queryString) throws Exception {
        String[] queries = queryString.split("&");
        Map<String, String> queriesMap = Arrays.stream(queries)
                .filter(s -> Pattern.matches("^(timestamp=\\d+)|(nonce=\\d+)|(sign=.+)$", s))
                .map(s -> {
                    String[] kv = s.split("=");
                    Map<String, String> map = new HashMap<>(1);
                    map.put(kv[0], kv[1]);
                    return map;
                }).reduce(
                        new HashMap<>(3),
                        (acc, to) -> {
                            acc.putAll(to);
                            return acc;
                        }
                );
        if (queriesMap.size() != 3) {
            throw new Exception("重放攻击缺少参数");
        }
        long timestamp = Long.parseLong(queriesMap.get("timestamp"));
        long nonce = Long.parseLong(queriesMap.get("nonce"));
        String sign = queriesMap.get("sign");

        return new ReplayAttacks(timestamp, nonce, sign);
    }

}
