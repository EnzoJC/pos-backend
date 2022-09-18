package pe.edu.upn.pos.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "POS API",
                version = "1.0",
                contact = @Contact(
                        name = "Enzo Carrión", email = "enz10.cr@gmail.com", url = "https://www.enzojc.com"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "http://localhost:8888",
                description = "Development"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Provide the JWT token. JWT token can be obtained from the Login API. For testing, use the credentials <strong>test/test</strong>"
)
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {
}
