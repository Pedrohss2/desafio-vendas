package com.desafiospringboot.desafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguracao {

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de vendas")
                        .description("Um simples simtema de vendas")
                        .version("1.0.0").license(new License().name("Licensa do sistema").url("sistemavendas.com")));
    }
}
