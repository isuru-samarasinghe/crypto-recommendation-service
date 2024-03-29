package com.xm.cryptoservice.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig class is responsible for configuring Swagger for the
 * application.
 * It sets up the API information and the base package for the controllers.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.url}")
    private String contactUrl;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Value("${swagger.license}")
    private String license;

    @Value("${swagger.licenseUrl}")
    private String licenseUrl;

    /**
     * This method configures the Docket bean which Swagger uses to generate the API
     * documentation.
     * It sets the API information and the base package for the controllers.
     * 
     * @return a Docket object configured for the application
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xm.cryptoservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * This method creates the ApiInfo object which contains information about the
     * API.
     * 
     * @return an ApiInfo object with the API's information
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                title,
                description,
                version,
                termsOfServiceUrl,
                new Contact(contactName, contactUrl, contactEmail),
                license,
                licenseUrl,
                Collections.emptyList());
    }
}