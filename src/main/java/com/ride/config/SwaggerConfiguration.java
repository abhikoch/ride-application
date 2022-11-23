package com.ride.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Kochhar, Abhinav
 *
 */
@Configuration
@EnableSwagger2
@Profile("local")
public class SwaggerConfiguration {
	
	private static final String AUTH_HEADER_KEY = "Authorization";
	private static final String AUTH_TOKEN_TYPE = "Bearer";
	private static final String PARAM_TYPE_HEADER = "header";
	private static final String PARAM_DESCRIPTION = "Authentication token obtained from access token request";

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
        		.deepLinking(true)
        		.displayOperationId(false)
                .defaultModelsExpandDepth(5)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl("")
                .build();
    }
	
    @Bean
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
		List<Parameter> parameters = new ArrayList<>();
		
		parameterBuilder.name(AUTH_HEADER_KEY)
			.modelRef(new ModelRef("string"))
			.parameterType(PARAM_TYPE_HEADER)
			.description(PARAM_DESCRIPTION)
			.required(false)
			.build();
		
		parameters.add(parameterBuilder.build());
        
		return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("public-api")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
            	.paths(Predicates.not(PathSelectors.regex("/actuator.*")))
            	.build()
            	.apiInfo(getApiInfo()).pathMapping("")
            	.globalOperationParameters(parameters)
            	.securitySchemes(Arrays.asList(apiKey()))
            	.securityContexts(Collections.singletonList(securityContext()))
                .produces(new HashSet<String>(Arrays.asList("application/json")));
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
        		.securityReferences(defaultAuth())
        		.forPaths(PathSelectors.regex("/.*"))
        		.build();
	}
    
    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference(AUTH_TOKEN_TYPE, authorizationScopes));
	}
    
    private ApiKey apiKey() {
        return new ApiKey(AUTH_TOKEN_TYPE, AUTH_HEADER_KEY, PARAM_TYPE_HEADER);
	}

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Ride Application",
                "RIDE-REQUESTOR",
                "V 1.0",
                "TERMS OF SERVICE URL",
                new Contact("Abhinav Kochhar", "", "abhikoch@gmail.com"),
                "LICENSE",
                "",
                Collections.emptyList()
        );
    }
}