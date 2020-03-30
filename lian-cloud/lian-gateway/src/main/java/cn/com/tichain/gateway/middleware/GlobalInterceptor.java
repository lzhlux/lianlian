package cn.com.tichain.gateway.middleware;

import cn.com.tichain.gateway.bean.RouteWithRole;
import cn.com.tichain.gateway.interceptor.ReplayAttacksInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GlobalInterceptor implements WebMvcConfigurer {

    class RouteBean {
        public String url;
        public List<String> methods;

        public RouteBean(String url, List<String> methods) {
            this.url = url;
            this.methods = methods;
        }
    }

    @Autowired
    ReplayAttacksInterceptor interceptor;

    @Autowired
    public RouteWithRole routeWithRole;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = routeWithRole.getAllowUrl().stream().map(stringObjectMap -> {
            String url = (String) stringObjectMap.get("url");
            Map<String, String> methods = (LinkedHashMap<String, String>) stringObjectMap.get("methods");
            List<String> method = methods.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
            return new RouteBean(url, method);
        }).filter(i -> i.methods.size() != 1 || !i.methods.get(0).toLowerCase().equals("get"))
            .map(i-> i.url).collect(Collectors.toList());


//        registry.addInterceptor(interceptor).excludePathPatterns(list);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                String method = request.getMethod().toLowerCase();
                if(!Arrays.asList("put", "post").contains(method)) {
                    return false;
                }
                return StringUtils.startsWithIgnoreCase(request.getContentType(), "multipart/");
            }
        };
    }
}
