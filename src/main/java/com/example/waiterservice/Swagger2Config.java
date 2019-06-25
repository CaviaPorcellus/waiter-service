package com.example.waiterservice;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.example.waiterservice")
public class Swagger2Config {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select().paths(apiPaths()).build()
        .forCodeGeneration(true)
        .ignoredParameterTypes(ApiIgnore.class)
        .useDefaultResponseMessages(false);

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("SpringBucks Waiter Service")
        .description("SpringBucks Waiter Service")
        .version("1.0")
        .contact(new Contact("Kevin Jin", null, "kevin.jin01@sap.com"))
        .license("Apache License Version 2.0")
        .licenseUrl("https://github.com/CaviaPorcellus/waiter-service")
        .build();
  }

  private Predicate<String> apiPaths() {
    return PathSelectors.regex("/coffee/.*|/order/.*");
  }
}
