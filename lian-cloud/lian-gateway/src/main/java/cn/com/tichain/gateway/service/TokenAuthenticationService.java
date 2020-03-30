package cn.com.tichain.gateway.service;

import cn.com.tichain.gateway.bean.MethodMap;
import cn.com.tichain.gateway.bean.RouteWithRole;
import cn.com.tichain.gateway.mdel.Account;
import cn.com.tichain.gateway.mdel.AccountCredentials;
import cn.com.tichain.gateway.mdel.AccountDTO;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationService {

    //static final long EXPIRATIONTIME = 600000;     // 10分钟
    static final Long EXPIRATIONTIME = 86400000L;     // 1天
    // 通过环境变量获取 jwt secret
    static final String SECRET = System.getenv("JWTSECRET");            // JWT密码
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    // 连接路由的权限配置
    @Autowired
    public RouteWithRole routeWithRole;

    @Autowired
    public MethodMap methodMap;

    @Autowired
    AccountService accountService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    public void saveToken(int uid, String JWT, Long EXPIRATIONTIME) {
//
//        String key = "gatewayuid" + Integer.toString(uid);
//        Long time = EXPIRATIONTIME / 1000;
//        stringRedisTemplate.opsForValue().set(key, JWT, time, TimeUnit.SECONDS);
//        //stringRedisTemplate.opsForValue().get("aaa");
//    }

    // JWT生成方法
//    public void addAuthentication(HttpServletResponse response, Authentication auth)
//            throws Exception, IOException {
//        String account = auth.getName();
//        //获取用户
//        AccountDTO accountDot = accountService.findByUsername(account);
//
//        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//
//        int uid = ((Account) accountDot.getData()).getId();
//        // 生成JWT
//        String JWT = Jwts.builder()
//                // 保存权限（角色）
//                .claim("authorities", authorities)
//                .claim("uid", uid)
//                // 用户名写入标题
//                .setSubject(account)
//                // 有效期设置
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//                // 签名设置
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                .compact();
//
//        try {
//            // 存储token
//            this.saveToken(uid, JWT, EXPIRATIONTIME);
//            // 将 JWT 写入 body
//            ((Account) accountDot.getData()).setToken(JWT);
//            response.setContentType("application/json");
//            response.setStatus(HttpServletResponse.SC_OK);
//            //System.out.println("-------------" + JWT);
//            @Cleanup
//            PrintWriter out = response.getWriter();
//            out.write(new Gson().toJson(accountDot));
//        } catch (IOException e) {
//            //System.out.println("aaaa:"+ e);
//            e.printStackTrace();
//        }
//    }

    // JWT验证方法
    public Authentication getAuthentication(HttpServletRequest request) throws Exception{
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            // 拿到uid, 验证token是否过期
            String uid = claims.get("uid").toString();
            boolean bl = this.isSameInRedis(uid, token);

            if (!bl) {
                throw new Exception("用户于其它设备登录");
            }

            // 是否重放攻击
            bl = this.isReplay(uid, request);
            if(bl) {
                throw new Exception("该请求已经被处理过");
            }

            // 拿用户名
            String user = claims.getSubject();
            String authorities1 = claims.get("authorities").toString();
            // 得到 权限（角色）
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authorities1);

            if (user == null) {
                return null;
            }

            // 返回验证令牌
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, authorities)
                    : null;
        }

        return null;
    }

    /**
     * TO DO 重放攻击的验证未完成
     * 检查请求是否重放
     * @param uid
     * @param request
     * @return
     */
    public Boolean isReplay(String uid, HttpServletRequest request) {
//        String reqNonce = request.getAuthType("nonce");
//        String reqTime = request.get("timestamp");
//        String reqSign = request.get("sign");
//
//        // 验证时间是否过期
//
//        // 验证nonce是否重复
//        String key = "nonce" + uid;
//        String nonce = stringRedisTemplate.opsForValue().get(key);
//
//        if (nonce == null || nonce.equals(reqNonce)) {
//            return false;
//        }
        // 验证签名是否正确


        return false;
    }

    public HttpSecurity setUrlAuth(HttpSecurity http) {
        try {
            Iterator routeAndRoleList = routeWithRole.getRouteAndRole().iterator();
            // 遍历所有路径
            while (routeAndRoleList.hasNext()) {
                LinkedHashMap routeAndRole = (LinkedHashMap) routeAndRoleList.next();

                // 获取所有访问的方法
                LinkedHashMap methodList = (LinkedHashMap) routeAndRole.get("methods");
                // 获取路径允许的所有角色
                LinkedHashMap roleList = (LinkedHashMap) routeAndRole.get("roles");
                // 获取单个路径
                String url = (String) routeAndRole.get("url");

                Set methodSet = methodList.keySet();
                Set roleSet = roleList.keySet();
                // 遍历每个方法
                for (Object key : methodSet) {
                    String method = (String) methodList.get(key);
                    // 获取方法
                    HttpMethod httpMethod = methodMap.getHttpMethodByString(method);
                    // 遍历每个角色
                    for (Object key2 : roleSet) {
                        String role = (String) roleList.get(key2);
                        http
                                .authorizeRequests()
                                .antMatchers(httpMethod, url).hasAuthority(role);
                        System.out.println(
                                "-----------------:" + url + "  " + httpMethod + "  " + role);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("路径权限限制出错--------：");
            e.printStackTrace();
        }

        return http;
    }

    public HttpSecurity setAllowUrl(HttpSecurity http) {
        try {
            Iterator allowUrlList = routeWithRole.getAllowUrl().iterator();
            // 遍历所有路径
            while (allowUrlList.hasNext()) {
                LinkedHashMap allowUrl = (LinkedHashMap) allowUrlList.next();

                // 获取所有访问的方法
                LinkedHashMap methodList = (LinkedHashMap) allowUrl.get("methods");
                // 获取单个路径
                String url = (String) allowUrl.get("url");

                Set methodSet = methodList.keySet();
                // 遍历每个方法
                for (Object key : methodSet) {
                    String method = (String) methodList.get(key);
                    // 获取方法
                    HttpMethod httpMethod = methodMap.getHttpMethodByString(method);
                    http
                            .authorizeRequests()
                            .antMatchers(httpMethod, url).permitAll();
                }
            }
        } catch (Exception e) {
            System.out.println("路径开放权限出错--------：");
            e.printStackTrace();
        }

        return http;
    }

    public boolean isSameInRedis(String uid, String token) {
        String key = "gatewayuid" + uid;
        String value = stringRedisTemplate.opsForValue().get(key);
        return token.equals(value);

    }
}
