package cn.com.tichain.gateway.middleware;

import cn.com.tichain.gateway.mdel.Account;
import cn.com.tichain.gateway.mdel.AccountCredentials;
import cn.com.tichain.gateway.mdel.AccountDTO;
import cn.com.tichain.gateway.mdel.GrantedAuthorityImpl;
import cn.com.tichain.gateway.mdel.Result;
import cn.com.tichain.gateway.service.AccountService;
import cn.com.tichain.gateway.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;


@Component
class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    static final Long EXPIRATIONTIME =  12L * 60L * 60L * 1000L;     // 12 hours
    // 环境变量获取 secret key
    static final String SECRET = System.getenv("JWTSECRET");

    @Autowired
    AccountService accountService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/api/v1/login"));
        setAuthenticationManager(authenticationManager);
    }

    // sha256 加密
    public static String sha256(String text)  throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }

    public String saveToken(Account account, String password) throws Exception{
        try{
            int uid = account.getId();
            // 获取权限
//            List<String> roles = account.getRoles();
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            String salt = Long.toString(System.currentTimeMillis());
            String saltPassword = new StringBuilder(password).append(salt).toString();
            String shaPassword = sha256(saltPassword);
            // all app user should be as a normal user
            authorities.add(new GrantedAuthorityImpl("normal"));
            // 生成JWT
            String jwt = Jwts.builder()
                    // 保存权限（角色）
                    .claim("authorities", authorities)
                    .claim("uid", uid)
                    .claim("id", shaPassword)
                    // 用户名写入标题
                    .setSubject(account.getAccount())
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();

    //            // 存储token
            String key = "gatewayuid" + Integer.toString(uid);
            Long time = EXPIRATIONTIME / 1000;
            stringRedisTemplate.opsForValue().set(key, jwt, time, TimeUnit.SECONDS);
            return jwt;
        } catch (Exception e) {
            //System.out.println("aaaa:"+ e);
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)throws AuthenticationException, IOException, ServletException {

        try {
            String jsonString = CharStreams.toString(req.getReader());
            // JSON反序列化成 AccountCredentials
            AccountCredentials creds = new Gson().fromJson(jsonString, AccountCredentials.class);
            // 验证登录
            AccountDTO accountDto = accountService.login(creds);
            Account account = (Account)accountDto.getData();

            // 生成token并保存到redis
            String jwt = this.saveToken(account, creds.getPassword());
            // 将 JWT 写入 body
            account.setToken(jwt);
            accountDto.setData(account);
            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_OK);
            //System.out.println("-------------" + JWT);
            @Cleanup
            PrintWriter out = res.getWriter();
            out.write(new Gson().toJson(accountDto));
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(account, creds, null);
            return auth;
        }catch (Exception e) {
            Result result = new Result<String>(false, "", e.getMessage());

            @Cleanup
            PrintWriter out = res.getWriter();

            String jsonStr = result.toString();
            out.write(jsonStr);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        try {
            return;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        //response.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!", JSONObject.NULL));
    }
}
