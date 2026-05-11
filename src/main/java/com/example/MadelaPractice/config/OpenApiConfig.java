package com.example.MadelaPractice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI madelaPracticeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MadelaPractice API")
                        .version("1.0")
                        .description("REST-сервис организаций, офисов и пользователей. Ответы успешных операций и ошибок " +
                                "оборачиваются в ApiResponse.")
                        .contact(new Contact().name("MadelaPractice")));
    }
}
