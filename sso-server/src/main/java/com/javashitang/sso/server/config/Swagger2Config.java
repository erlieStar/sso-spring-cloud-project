package com.javashitang.sso.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: lilimin
 * @Date: 2019/5/21 13:15
 */
@Configuration
public class Swagger2Config {


    /** 访问http://localhost:8080/swagger-ui.html */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里写controller的包名
                .apis(RequestHandlerSelectors.basePackage("com.st.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("www.erlie.cc 编程视频资源共享网站")
                .termsOfServiceUrl("www.erlie.cc")
                .version("1.0")
                .build();
    }
}
