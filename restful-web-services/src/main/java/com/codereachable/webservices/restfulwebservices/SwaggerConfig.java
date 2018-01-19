package com.codereachable.webservices.restfulwebservices;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Configuration
@Configuration
// Enable Swagger
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact(
			"Yahav N. Hoffman", "https://www.CodeReachable.com", "info@codereachable.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"CodeReachable API", 
			"This set of API's represents the basic interactions and relations of users and thier courses",
			"1.0", "urn:tos", DEFAULT_CONTACT,
			"Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
	
	public static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
			new HashSet<String>();
			static {
				DEFAULT_PRODUCES_AND_CONSUMES.add("application/json");
			}
	// Control Swagger2 docs on uri:
	/*
	 * 	-	/v2/api-docs
	 * 	-	/configuration/ui
	 * 	-	/configuration/security
	 * 	-	/swagger-resources
	 * 	-	/swagger-ui.html
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	

}
