package com.lianlian.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** swagger-ui 配置文件 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        System.out.println("--------------------------------------------------------------------");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.buildApiInfo()) // .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lianlian.main.main.controller"))// 需要生成文档的顶级包的位置
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("链链(对外)模块接口详情")
                .description("链链(对外)模块接口详情")
                .termsOfServiceUrl("http://www.skyworth.com")
                .contact("swagger , see more at ")
                .version("1.0.0")
                .build();
    }

}