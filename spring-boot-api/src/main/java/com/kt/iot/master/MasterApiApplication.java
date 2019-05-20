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

package com.kt.iot.master;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kt.iot.Const;
import com.kt.iot.base.filter.LoggerFilter;

@Configuration
@ComponentScan(basePackages = {Const.PACKAGE})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//@EnableEurekaClient
@EnableWebMvc  //Swagger UI를 위해 임시로 추가
@EnableScheduling
@PropertySources({
	@PropertySource(value="file:${properties.path}/application.properties")
})
public class MasterApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer {


	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		return configurer;
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MasterApiApplication.class);
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		servletContext.addListener(new ContextLoaderListener(context));
//		context.setConfigLocation(Const.PACKAGE);
//		context.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
		servlet.setMultipartConfig(new MultipartConfigElement("", 1024*1024*50, 1024*1024*100, 1024*1024*10));
		servlet.setAsyncSupported(true);

		// CharacterSet Encoding
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic charEncRegi = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        charEncRegi.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        charEncRegi.setAsyncSupported(true);

        FilterRegistration.Dynamic loggerFilter = servletContext.addFilter("loggerFilter", new LoggerFilter());
        EnumSet<DispatcherType> dispatcherTypesForLoggerFilter = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD);
        loggerFilter.addMappingForUrlPatterns(dispatcherTypesForLoggerFilter, true, "/*");
        loggerFilter.setAsyncSupported(true);

//		FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
//		corsFilter.addMappingForUrlPatterns(null, false, "/*");

      
		super.onStartup(servletContext);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(MasterApiApplication.class, args);
	}
}
