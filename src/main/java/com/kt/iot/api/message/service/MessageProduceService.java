package com.kt.iot.api.message.service;

import java.util.Map;

import org.springframework.web.context.request.async.DeferredResult;

import com.kt.iot.api.message.vo.KafkaMsgType;

public interface MessageProduceService {
	
	void resourceCreateMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions );
	void resourceUpdateMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions );
	void resourceDeleteMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions );
	
	String genTransactionKey();
	String genE2eTransactionId();
	
}
