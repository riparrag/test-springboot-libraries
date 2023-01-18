package com.ipasoft.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI springShopOpenApi() {
		return new OpenAPI().info(new Info().title("Ipa Api")
											.description("ipasoft api")
											.version("v1.0")
											.license(new License().name("ipasoft S.R.L.")));
	}
	
	/*
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
													  .apis(RequestHandlerSelectors.any())
													  .paths(PathSelectors.any())
													  .build();
	}
	

	private final static String CONFIGURACION_YAML = "paas-bcra-controles";
	
    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            Arrays.asList(CONFIGURACION_YAML)
                  .forEach(resourceName -> resources.add(loadResource(resourceName)));
            return resources;
        };
    }

    private SwaggerResource loadResource(String resource) {

        SwaggerResource wsResource = new SwaggerResource();
        wsResource.setName(resource);
        wsResource.setSwaggerVersion("2.0");
        wsResource.setLocation("/api-docs/" + resource + ".yaml");

        return wsResource;
    }
*/
}