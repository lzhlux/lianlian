package cn.com.tichain.gateway.controller;

import cn.com.tichain.gateway.mdel.Result;
import cn.com.tichain.gateway.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@RestController
public class Account {
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    static final String SECRET = System.getenv("JWTSECRET");            // JWT密码
    static final Long EXPIRATIONTIME =  2L * 24L * 60L * 60L * 1000L;     // 2天
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/api/v1/loginout", method= RequestMethod.POST)
    @ResponseBody
    String loginout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        try {
            String token = req.getHeader(HEADER_STRING);
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            // 拿到uid, 验证token是否过期
            String uid = claims.get("uid").toString();
            String key = "gatewayuid" + uid;
            stringRedisTemplate.delete(key);

            Result result = new Result<String>(true, "", "删除token成功");
            return result.toString();

        } catch (Exception e) {
            Result result = new Result<String>(false, "", e.getMessage());
            return result.toString();
        }



    }


    @RequestMapping(value="/api/v1/replaceToken", method= RequestMethod.POST)
    @ResponseBody
    String replaceToken(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        try {
            String token = req.getHeader(HEADER_STRING);
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            // 拿到uid, 验证token是否过期
            String uid = claims.get("uid").toString();
            String key = "gatewayuid" + uid;
            String oldKey = stringRedisTemplate.opsForValue().get(key);
            if (!oldKey.equals(token)){
                Result result = new Result<String>(false, "", "旧token验证不通过!");
                return result.toString();
            }
            String id = claims.get("id").toString();
            ArrayList<GrantedAuthority> authorities = (ArrayList<GrantedAuthority>)claims.get("authorities");
            String subject = claims.getSubject();
            // 生成JWT
            String jwt = Jwts.builder()
                    // 保存权限（角色）
                    .claim("authorities", authorities)
                    .claim("uid", uid)
                    .claim("id", id)
                    // 用户名写入标题
                    .setSubject(subject)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
            //更新token
            Long time = EXPIRATIONTIME / 1000;
            stringRedisTemplate.delete(key);
            stringRedisTemplate.opsForValue().set(key, jwt, time, TimeUnit.SECONDS);
            Result result = new Result<String>(true, jwt, "更换token成功!");
            return result.toString();
        } catch (Exception e) {
            Result result = new Result<String>(false, "", e.getMessage());
            return result.toString();
        }



    }
}
