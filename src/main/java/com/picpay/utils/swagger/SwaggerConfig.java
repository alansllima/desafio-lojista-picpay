package com.picpay.utils.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("API Lojista")
                .version("1.0")
                .description("API de criacão de cadastro de clientes e transações monetárias"));
    }
}

