package com.lianlian.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PathConfig extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/resources/**").addResourceLocations("/usr/local/resources/");
    	registry.addResourceHandler("/resources/**").addResourceLocations("file:F:/resources/");
        super.addResourceHandlers(registry);
    }
}
 
