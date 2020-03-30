package cn.com.tichain.gateway.mdel;

import cn.com.tichain.gateway.service.AccountService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication;
//        try {
//            // 获取认证的用户名 & 密码
//            AccountCredentials creds = (AccountCredentials)authentication.getCredentials();
//            //AccountCredentials creds = new Gson().fromJson(_creds.toString(), AccountCredentials.class);
//            String account = creds.getAccount();
//            String password = creds.getPassword();
//
//            // 登录账户
//            Account user = accountService.login(creds);
//            // 认证逻辑
//            // 获取权限
//            List<String> roles = user.getRoles();
//            // 这里设置权限和角色
//            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//
//            for(Iterator<String> items = roles.iterator();items.hasNext();)    {
//              authorities.add( new GrantedAuthorityImpl(items.next()) );
//            }
////                authorities.add( new GrantedAuthorityImpl("USER") );
//            // 生成令牌
//            Authentication auth = new UsernamePasswordAuthenticationToken(account, password, authorities);
//            return auth;
//
//        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
//        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}