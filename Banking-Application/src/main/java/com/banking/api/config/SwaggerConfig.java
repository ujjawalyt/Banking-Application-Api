package com.banking.api.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)

				.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {

		Contact contact = new Contact("Ujjawal Prakash", "https://github.com/ujjawalyt/Banking-Application-Api",
				"ujjawalyt@gmail.com");

		return new ApiInfoBuilder().title("Banking-Application-Api")
				.description("This Project is developed by Ujjawal Prakash").version("1.0.0").contact(contact)
				.license("Licence of APIS").build();

	}
}
