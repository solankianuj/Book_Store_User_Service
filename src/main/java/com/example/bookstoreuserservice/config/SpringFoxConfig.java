package com.example.bookstoreuserservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * purpose-configure swagger.
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bookstoreuserservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {

        return new ApiInfo("Note Book System",

                "APIs for E.",

                "1.0",

                "Terms of service",



                new Contact("Note Book System", "http://NoteBook.com", "anujaj2222@gmail.com"),

        "Apache 2.0",

                "http://www.apache.org/licenses/LICENSE-2.0&quot",

        Collections.emptyList());

    }

}
