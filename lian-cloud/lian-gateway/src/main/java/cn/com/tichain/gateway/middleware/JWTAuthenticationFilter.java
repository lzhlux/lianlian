package cn.com.tichain.gateway.middleware;

import cn.com.tichain.gateway.mdel.Result;
import cn.com.tichain.gateway.service.TokenAuthenticationService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
class JWTAuthenticationFilter extends GenericFilterBean {

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {
        try {
            Authentication authentication = tokenAuthenticationService
                    .getAuthentication((HttpServletRequest)request);

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(request,response);
        } catch (Exception e) {

            HttpServletResponse resp =(HttpServletResponse) response;
            resp.setStatus(200);
            Result result = new Result<String>(false, "403", e.toString());
            @Cleanup
            PrintWriter out = resp.getWriter();
            out.write(result.toString());
        }
    }
}