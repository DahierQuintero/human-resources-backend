package dq.hr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Human Resources System API")
                        .description("API with Java + Spring Boot documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dilan Quintero")
                                .email("dilandahier@gmail.com")
                                .url("https://www.linkedin.com/in/dilandahierquinteror-backend-developer/"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor de desarrollo"),
                        new Server().url("https://tu-api-produccion.com").description("Servidor de producción")
                ));
    }
}
