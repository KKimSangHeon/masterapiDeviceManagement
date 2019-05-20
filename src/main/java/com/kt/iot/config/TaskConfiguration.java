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

/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.config
 * </PRE>
 * @brief
 * @file TaskConfiguration.java
 * @date 2015. 5. 11. 오후 5:53:57
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 5. 11.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <PRE>
 *  ClassName TaskConfiguration
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 5. 11. 오후 5:53:57
 * @author kim.seokhun@kt.com
 */
@Configuration
public class TaskConfiguration {

	@Bean
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(500);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(5000);
		return executor;
	}
}
