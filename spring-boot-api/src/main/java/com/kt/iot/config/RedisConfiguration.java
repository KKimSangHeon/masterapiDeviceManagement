///*
// * GiGA IoT Platform version 2.0
// *
// *  Copyright ⓒ 2015 kt corp. All rights reserved.
// *
// *  This is a proprietary software of kt corp, and you may not use this file except in
// *  compliance with license agreement with kt corp. Any redistribution or use of this
// *  software, with or without modification shall be strictly prohibited without prior written
// *  approval of kt corp, and the copyright notice above does not evidence any actual or
// *  intended publication of such software.
// */
//
///**
// *
// */
//package com.kt.iot.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
///**
// * @author Sangsun Park (blue.park@kt.com)
// *
// */
///*@Configuration
//@EnableCaching*/
//public class RedisConfiguration {
//
//	@Value("${redis.hostName}")
//    private String hostName;
//
//    @Value("${redis.port}")
//    private int port;
//
//	@Bean
//	public JedisConnectionFactory jedisConnFactory(){
//		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
//		jedisConnFactory.setHostName(this.hostName);
//		jedisConnFactory.setPort(this.port);
//		//jedisConnFactory.setPassword(this.password);
//		return jedisConnFactory;
//	}
//
//	@Bean
//	public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnFactory){
//		RedisTemplate redisTemplate = new RedisTemplate();
//		redisTemplate.setConnectionFactory(jedisConnFactory);
//		return redisTemplate;
//	}
//}
