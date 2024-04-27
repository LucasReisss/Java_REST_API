package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java and Spring Boot 3")
						.version("v1")
						.description("RESTful API")
						.termsOfService("https://docs.google.com/document/d/17CovW2EwaHteY2YDIFPt1o_b7Y0ACZMMrV0cJUlo87M/edit?usp=sharing")
						.license(new License().name("Apache 2.0")
								.url("https://docs.google.com/document/d/17CovW2EwaHteY2YDIFPt1o_b7Y0ACZMMrV0cJUlo87M/edit?usp=sharing")
								)
						);
	}
	
}
