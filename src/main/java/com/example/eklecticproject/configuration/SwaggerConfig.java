package com.example.eklecticproject.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());

    }


    public Info infoAPI() {
        return new Info().title(" EPROJECT ")
                .description("Eproject")
                .contact(contactAPI());
    }

    public Contact contactAPI() {
        return new Contact().name("FOURAT")
                .email("fourat.cherif@esprit.tn")
                .url("");
    }


    @Bean
    public GroupedOpenApi productPublicApi() {
        return GroupedOpenApi.builder()
                .group("EPROJECT Management API")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();
    }


}
