package cn.com.tichain.gateway;

import cn.com.tichain.gateway.middleware.PreRequestLogFilter;
import com.squareup.okhttp.MediaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication

public class GatewayApplication {
  @Bean
  public PreRequestLogFilter preRequestLogFilter() {
    return new PreRequestLogFilter();
  }

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(GatewayApplication.class, args);

  }
}
