package com.gymbo.authenticationservice.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Pol Farreny Capdevila",
                        email = "polfarreny@gmail.com",
                        url = "https://www.polfarrenycapdevila.com"
                ),
                description = "OpenApi documentation for Gymbo - Authentication Service REST API",
                title = "OpenApi specification - Gymbo - Authentication Service",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8086"
                )
        }
)
public class OpenApiConfiguration {
}
