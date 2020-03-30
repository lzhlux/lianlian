package cn.com.tichain.gateway.middleware;

import cn.com.tichain.gateway.bean.MethodMap;
import cn.com.tichain.gateway.bean.RouteWithRole;
import cn.com.tichain.gateway.mdel.CustomAuthenticationProvider;
import cn.com.tichain.gateway.service.TokenAuthenticationService;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthHandler extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomAuthenticationProvider customAuthenticationProvider;

  @Autowired
  JWTAuthenticationFilter jWTAuthenticationFilter;

  @Autowired
  TokenAuthenticationService tokenAuthenticationService;

  @Value("${spring.data.rest.basePath}") private String basePath;
  /**
   * 配置config中routeAndRole的内容
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    //http = tokenAuthenticationService.setUrlAuth(http);
    http = tokenAuthenticationService.setAllowUrl(http);
    // 设置资源服务器的访问权限
    http
        .csrf().disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
        .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
            .addFilterBefore(jwtAdminLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器验证其他请求的Token是否合法
        .addFilterBefore(jWTAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 使用自定义身份验证组件
    auth.authenticationProvider(customAuthenticationProvider);

  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  JWTLoginFilter jwtLoginFilter() throws Exception {
    JWTLoginFilter jwtLoginFilter =  new JWTLoginFilter(authenticationManagerBean());
    return jwtLoginFilter;
  }
  @Bean
  JWTAdminLoginFilter jwtAdminLoginFilter() throws Exception {
    JWTAdminLoginFilter jwtAdminLoginFilter = new JWTAdminLoginFilter(authenticationManagerBean());
    return jwtAdminLoginFilter;
  }

}

