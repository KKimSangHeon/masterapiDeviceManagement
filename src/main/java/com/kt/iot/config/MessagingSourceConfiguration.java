package com.kt.iot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kt.iot.api.message.service.AbstractMessageConsumeService;
import com.kt.iot.api.message.service.AbstractMessageProduceService;
import com.kt.iot.api.message.service.KafkaMessageConsumeService;
import com.kt.iot.api.message.service.KafkaMessageProduceService;
import com.kt.iot.api.message.service.MessageConsumeService;
import com.kt.iot.api.message.service.MessageProduceService;

@Configuration
public class MessagingSourceConfiguration {
	
	@Value("${message.queue.type}")
	private String messageQueueType;
	
	
	
	@Bean
	public MessageProduceService produceService() {
		MessageProduceService produceService = null;
	
			produceService = new KafkaMessageProduceService();
	
//		MessageConsumeService consumeService = consumeService();
//		((AbstractMessageProduceService) produceService).setMessageConsumeService(consumeService);
//		((AbstractMessageConsumeService) consumeService).setMessageProduceService(produceService);
		return produceService;
	}
	
	@Bean
	public MessageConsumeService consumeService() {
		MessageConsumeService service = null;
			service = new KafkaMessageConsumeService();
		return service;
	}
//	
}
