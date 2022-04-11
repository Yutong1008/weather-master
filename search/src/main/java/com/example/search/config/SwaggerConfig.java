package com.example.search.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createSwagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.search.controller.SearchController"))
                .paths(PathSelectors.any()).build();
    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Weather Search Service")
//                .description("By use this service we can search one or more weather information of cities")
//                .termsOfServiceUrl("http://localhost:9001/weather/search")
//                .version("1.0")
//                .build();
//
//    }

}
