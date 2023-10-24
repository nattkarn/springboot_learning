package com.testing.api.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        String version = "1.0";
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Api")
                        .version(version)
                        .description("This project is learning for backend api.")
                        .contact(new Contact()
                                .name("Nattkarn Prajansri")
                                .email("nattkarn.p@hotmail.com")));
    }
}
