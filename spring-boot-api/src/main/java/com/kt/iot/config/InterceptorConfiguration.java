package com.kt.iot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kt.iot.base.interceptor.LoggingHandlerInterceptor;


/**
 * <PRE>
 *  ClassName InterceptorConfiguration
 * </PRE>
 * @brief
 * @version 1.0
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingHandlerInterceptor()).addPathPatterns("/**");
    }

}
