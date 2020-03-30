package com.lianlian.security;


import com.lianlian.common.result.ResultCode;
import com.lianlian.common.result.ResultData;
import com.lianlian.common.token.TokenConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class wJwtAuthenticationTokenFilter extends OncePerRequestFilter {


    private static final String[] NOT_FILTER = new String[]{
            "/wx/auth",
            "/wx/pay/orderWxPayNotify",
            "/wx/commodity",
            "/wx/collect/isCollect",
            "/wx/gouwuche/findGouwucheNum",
            "/wx/evaluate",
            "wx/integralSprint"
    };

    private static final String[] NOT_SIGN = new String[]{
            "/wx/pay/orderWxPayNotify",
    };


    @Autowired
    private WShopJwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenConfig tokenConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        boolean doFilter = false;
        if (uri.startsWith("/wx")) {
            doFilter = true;
            for (String notFilter : NOT_FILTER) {
                if (uri.indexOf(notFilter) != -1) {
                    doFilter = false;
                    break;
                }
            }
        }
        doFilter = false;//TODO删除  ,前期不授权用


        boolean doSign = true;
        for (String notSign : NOT_SIGN) {
            if (uri.indexOf(notSign) != -1) {
                doSign = false;
                break;
            }
        }

        if (doSign) {
            //TODO
//			String sign = request.getHeader("Sign"); 
//			String timestamp = request.getHeader("timestamp"); 
//			if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)) { 
//				ObjectMapper mapper = new ObjectMapper();
//				response.setContentType("application/json;charset=utf-8");
//				response.getWriter().write(mapper.writeValueAsString(ResultData.warn(ResultCode.SIGN_ERROR))); 
//				return; 
//			} 
//
//			//获取参数 
//			Enumeration<String> pNames = request.getParameterNames(); 
//			StringBuffer sb = new StringBuffer(); 
//			while(pNames.hasMoreElements()){ 
//				String name = (String)pNames.nextElement(); 
//				String value = request.getParameter(name); 
//				if (!StringUtils.isEmpty(name)) {
//					sb.append(name).append("=").append(value).append("&"); 
//				} 
//			} 
//			String queryStr = sb.toString(); 
//			if (StringUtils.isEmpty(queryStr)) {
//				queryStr = "timestamp=" + timestamp; 
//			} else { 
//				queryStr = queryStr + "&timestamp=" + timestamp; 
//			} 
//			String[] keys = queryStr.split("&");
//			Arrays.sort(keys); 
//			StringBuffer reqStr = new StringBuffer(); 
//			for(int i = 0; i < keys.length; i++) { 
//				if (!StringUtils.isEmpty(keys[i])) {
//					reqStr. append(keys[i] + "&"); 
//				} 
//			}
//			reqStr.append("key=yVwlsbIrY3q22EnoYYM4nR5zqTmqed05");
//			
//			String signAfter = EncryptUtils.md5Encode(reqStr.toString()).toUpperCase(); 
//			if (!sign.equals(signAfter)) { 
//				ObjectMapper mapper = new ObjectMapper();
//				response.setContentType("application/json;charset=utf-8");
//				response.getWriter().write(mapper.writeValueAsString(ResultData.warn(ResultCode.SIGN_ERROR))); 
//				return; 
//			}
        }

        if (uri.contains("/swagger") || uri.contains("templates") || uri.contains("v2") || uri.contains("webjars"))
            doFilter = false;
        /*if (doFilter) {
            String authHeader = request.getHeader(tokenConfig.getHeader());
            if (authHeader != null && authHeader.startsWith(tokenConfig.getTokenHead())) {
                final String authToken = authHeader.substring(tokenConfig.getTokenHead().length());
                String userId = jwtTokenUtil.getUserIdFromToken(authToken);
                if (!StringUtils.isEmpty(userId)) {
                    if (!jwtTokenUtil.validateToken(authToken)) {
                        ObjectMapper mapper = new ObjectMapper();
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
                        return;
                    } else {
                        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
                        m.put("userId", new String[]{userId});
                        request = new ParameterRequestWrapper(request, m);
                    }
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
                    return;
                }
            } else {
                ObjectMapper mapper = new ObjectMapper();
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(mapper.writeValueAsString(ResultData.error(ResultCode.UNAUTHTOKEN)));
                return;
            }
        } else {
            String authHeader = request.getHeader(tokenConfig.getHeader());
            if (authHeader != null && authHeader.startsWith(tokenConfig.getTokenHead())) {
                final String authToken = authHeader.substring(tokenConfig.getTokenHead().length());
                String userId = jwtTokenUtil.getUserIdFromToken(authToken);
                if (!StringUtils.isEmpty(userId)) {
                    if (jwtTokenUtil.validateToken(authToken)) {
                        Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
                        m.put("userId", new String[]{userId});
                        request = new ParameterRequestWrapper(request, m);
                    }
                }
            }
        }*/

        chain.doFilter(request, response);
    }
}
