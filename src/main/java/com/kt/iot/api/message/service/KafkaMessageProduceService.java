package com.kt.iot.api.message.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

import com.kt.iot.Env;
import com.kt.iot.api.message.vo.KafkaMsgType;
import com.kt.iot.api.message.vo.KafkaOperationType;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaMessageProduceService extends AbstractMessageProduceService {
	
	@Value("${producer.api.broker}")
	private String broker;
	
//	@Value("${producer.api.topic}")
	private String topic;
	
	private Producer<String, byte[]> producer;
	
	@PostConstruct
	private void initialize() {

		
		topic = System.getenv(Env.PRODUCE_TOPIC);
		Properties propsObj = new Properties();
		
		propsObj.put("metadata.broker.list", broker);
		propsObj.put("serializer.class", "kafka.serializer.DefaultEncoder");
		propsObj.put("partitioner.class", "kafka.producer.DefaultPartitioner");
		propsObj.put("request.required.acks", "0");
		
		
		producer = new Producer<String,byte[]>(new ProducerConfig(propsObj));
	}

	@PreDestroy
	public void destroy() {
		producer.close();
	}

//	public void sendMessage(String message) { 
//		
//		KeyedMessage<String,String> data = new KeyedMessage<String, String>("test", message);
//		System.out.println("in sendMessage:"+message);
//		producer2.send(data);
//	}

	@Override
	public void sendMessage(byte[] message) {
		// TODO Auto-generated method stub
		KeyedMessage<String,byte[]> data = new KeyedMessage<String, byte[]>(topic, message);
		producer.send(data);
		
	}

	@Override
	public void resourceCreateMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions ) {
		// TODO Auto-generated method stub
		if(extensions == null){
			extensions = new HashMap<>();
			settingOperationType(extensions,KafkaOperationType.CREATE);
		}
		sendMessage(message, KafkaMsgType.INITA_DEV_UDATERPRT, serviceCode, transactionKey, e2eTransactionId, extensions);
	}

	@Override
	public void resourceUpdateMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions ) {
		// TODO Auto-generated method stub
		if(extensions == null){
			extensions = new HashMap<>();
			settingOperationType(extensions,KafkaOperationType.UPDATE);
		}
		sendMessage(message, KafkaMsgType.INITA_DEV_UDATERPRT, serviceCode, transactionKey, e2eTransactionId, extensions);
	}

	@Override
	public void resourceDeleteMessage(String message,String serviceCode,String transactionKey, String e2eTransactionId,Map<String,String> extensions ) {
		// TODO Auto-generated method stub
		if(extensions == null){
			extensions = new HashMap<>();
			settingOperationType(extensions,KafkaOperationType.DELETE);
		}
		sendMessage(message, KafkaMsgType.INITA_DEV_UDATERPRT, serviceCode, transactionKey, e2eTransactionId, extensions);
	}

}
