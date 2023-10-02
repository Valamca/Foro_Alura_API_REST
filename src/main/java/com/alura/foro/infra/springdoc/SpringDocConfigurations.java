package com.alura.foro.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;

@Configuration
public class SpringDocConfigurations {

	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(new Components()
	          .addSecuritySchemes("bearer-key",
	          new SecurityScheme()
	          .type(SecurityScheme
	          .Type.HTTP)
	          .scheme("bearer")
	          .bearerFormat("JWT")))
	          .info(new Info()
	        		  .title("API Foro Alura")
	        		  .description("API Rest para la aplicación del Foro Alura, que contiene las funcionalidades CRUD de Usuarios, Temas, Cursos, Respuestas y Autenticación(Login), tanto para su "
	        		  		+ " registro, listado, actualización y eliminación de elementos.")
	        		  .contact(new Contact()
	        		  .name("Equipo Backend")
	        		  .email("Francisco_Valam@hotmail.com"))
	        		  .license(new License()
	        		  .name("Apache 2.0")
	        		  .url("https://www.linkedin.com/in/franciscovalamca")));
	          
	}
	
	@PostConstruct
	public void message() {
		System.out.println("bearer is working");
	}	
	
}
