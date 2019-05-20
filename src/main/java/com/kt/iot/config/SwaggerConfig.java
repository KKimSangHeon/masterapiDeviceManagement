/*
 * GiGA IoT Platform version 2.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */

package com.kt.iot.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.kt.iot.Version;
//import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
//import com.mangofactory.swagger.models.dto.ApiInfo;
//import com.mangofactory.swagger.paths.RelativeSwaggerPathProvider;
//import com.mangofactory.swagger.plugin.EnableSwagger;
//import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements ServletContextAware {

//    private SpringSwaggerConfig springSwaggerConfig;

    private ServletContext servletContext;

    public static final String[] DEFAULT_INCLUDE_PATTERN = {"/" + Version.V11 + "/.*", "/" + Version.V2 + "/.*"};

    /*@Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        RelativeSwaggerPathProvider relativeSwaggerPathProvider = new RelativeSwaggerPathProvider(servletContext);
        relativeSwaggerPathProvider.setApiResourcePrefix("masterapi");
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo())
                .pathProvider(relativeSwaggerPathProvider).includePatterns(DEFAULT_INCLUDE_PATTERN);
    }*/
    
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_12)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
//          .paths(PathSelectors.ant("/v**"))
          .paths(PathSelectors.any())
          .build()
//          .apiInfo(apiInfo())
          /*.useDefaultResponseMessages(false)
          .globalResponseMessage(requestMethod, responseMessages)*/
          ;
    }

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
			"template API",
			"template API 정보",
			"",
			"",
			"",
			"",
			""
			);
		return apiInfo;
	}

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}