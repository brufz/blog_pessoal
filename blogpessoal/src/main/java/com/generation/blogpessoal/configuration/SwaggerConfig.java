package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springBlogPessoalAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto blog pessoal")
						.description("Projeto blog pessoal - Generation Brasil")
						.version("v.0.0.1")
						.license(new License()
								.name("Bruna Franchi")
								.url("https://www.linkedin.com/in/bruna-franchi/"))
						.contact(new Contact()
								.name("Bruna Franchi")
								.url("https://www.github.com/brufz")
								.email("bfzdev@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("GitHub")
						.url("https://www.github.com/brufz"));
	}
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return openAPI -> {
			openAPI.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				ApiResponses apiResponses = operation.getResponses();
				
				 apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				 apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
				 apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
				 apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
				 apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
				 apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
				 apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
			}));
		
		};
		
		
		}
		private ApiResponse createApiResponse(String message) {
			return new ApiResponse().description(message);
	}

}
