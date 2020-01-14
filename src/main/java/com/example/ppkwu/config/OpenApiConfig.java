package com.example.ppkwu.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "vcard API",
                version = "v1",
                description = "vcard",
                contact = @Contact(
                        name = "Natalia",
                        email = "209286@edu.p.lodz.pl"
                )
        )
)
public class OpenApiConfig {
}
