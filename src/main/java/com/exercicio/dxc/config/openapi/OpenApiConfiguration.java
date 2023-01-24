package com.exercicio.dxc.config.openapi;

import java.util.StringJoiner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Configuration
public class OpenApiConfiguration {
	private static final String SCHEME_NAME = "Bearer";
    private static final String SCHEME = "Bearer";

	@Bean
	protected OpenAPI customOpenAPI() {
		OpenAPI openApi = new OpenAPI().info(getInfo());
		addSecurity(openApi);
		return openApi;
	}

	private Info getInfo() {
		return new Info()
				.title("Exercicio para entrevista na DXC")
				.version("1.0.0")
				.license(getLicense());
	}
	
	private License getLicense() {
		return new License().name("Unlicense").url("https://unlicense.org/");
	}

	private void addSecurity(OpenAPI openApi) {
		Components components = createComponents();
		SecurityRequirement securityItem = new SecurityRequirement().addList(SCHEME_NAME);
		openApi.components(components).addSecurityItem(securityItem);
	}

	private Components createComponents() {
		Components components = new Components();
		components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());
		return components;
	}

	private SecurityScheme createSecurityScheme() {		
		return new SecurityScheme()
				.name(SCHEME_NAME)
				.type(SecurityScheme.Type.APIKEY)
				.scheme(SCHEME)
				.name("Authorization")
                .description(createDescription())
                .in(In.HEADER);
	}

	private String createDescription() {
		StringJoiner description = new StringJoiner("<br />");
		description.add("JWT Authorization header using the Bearer scheme.");
		description.add("Enter 'Bearer' [space] and then your token in the text input below.");
		description.add("Example: \"Bearer 12345abcdef\"");
		return description.toString();
	}
}
