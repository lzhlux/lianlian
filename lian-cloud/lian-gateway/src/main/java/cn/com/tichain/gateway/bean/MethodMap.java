package cn.com.tichain.gateway.bean;

import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class MethodMap {

  public HashMap<String, HttpMethod> getMethod() {
    return method;
  }

  public void setMethod(HashMap<String, HttpMethod> method) {
    this.method = method;
  }

  public HttpMethod getHttpMethodByString(String method) {
    return this.method.get(method.toUpperCase());
  }

  HashMap<String, HttpMethod> method;

  MethodMap() {
    method = new HashMap<String, HttpMethod>();
//    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    method.put("GET", HttpMethod.GET);
    method.put("HEAD", HttpMethod.HEAD);
    method.put("POST", HttpMethod.POST);
    method.put("PUT", HttpMethod.PUT);
    method.put("PATCH", HttpMethod.PATCH);
    method.put("DELETE", HttpMethod.DELETE);
    method.put("OPTIONS", HttpMethod.OPTIONS);
    method.put("TRACE", HttpMethod.TRACE);
  }
}
