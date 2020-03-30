package com.lianlian.aop;

import com.lianlian.common.result.ResultData;
import com.lianlian.util.IpUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


@Aspect
@Component
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(* com.lianlian..*.*(..)) ")
    public void webLog() {

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object result = pjp.proceed();

            if (result instanceof ResultData) {
                ObjectMapper mapper = new ObjectMapper();
                // null替换为""  
                mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
                        arg1.writeString("");
                    }
                });
                String str = mapper.writeValueAsString(result);
                result = mapper.readValue(str, ResultData.class);
            }
            return result;
        } catch (Exception e) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (!StringUtils.isEmpty(attributes)) {
                HttpServletRequest request = attributes.getRequest();
                logger.error("uri: " + request.getRequestURI());
                logger.error("header: " + request.getHeader("Authorization"));
                logger.error("method: " + request.getMethod());
                logger.error("ip: " + IpUtils.getIpAddr(request));
                //获取参数
                Enumeration<String> pNames = request.getParameterNames();
                StringBuffer sb = new StringBuffer();
                while (pNames.hasMoreElements()) {
                    String name = (String) pNames.nextElement();
                    String value = request.getParameter(name);
                    if (!StringUtils.isEmpty(name)) {
                        sb.append(name).append("=").append(value).append("&");
                    }
                }
                logger.error("query: " + sb.toString());
                logger.error("reason: " + e.getMessage());
            }
            e.printStackTrace();
            //	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        } catch (Throwable e) {
            e.printStackTrace();
            //	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception();
        }
    }
}
