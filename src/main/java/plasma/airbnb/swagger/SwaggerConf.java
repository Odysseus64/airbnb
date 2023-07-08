package plasma.airbnb.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConf {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components( new Components()
                        .addSecuritySchemes( "Bearer Token", apiKeySecuritySchema() ) )
                .info( new Info().title( "AirBNB" ).description( "Written by: AirBNB" ) )
                .security( List.of( new SecurityRequirement().addList( "Bearer Token" ) ) );
    }

    private SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name( "Authorization" )
                .description( "put your jwt token here!" )
                .in( SecurityScheme.In.HEADER )
                .type( SecurityScheme.Type.HTTP )
                .scheme( "Bearer" );
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
