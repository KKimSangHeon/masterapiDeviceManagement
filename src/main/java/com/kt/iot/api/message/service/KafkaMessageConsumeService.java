package com.kt.iot.api.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import com.kt.iot.Const;
import com.kt.iot.Env;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

//@Scope("singleton")
public class KafkaMessageConsumeService extends AbstractMessageConsumeService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumeService.class);

//	@Value("${consumer.api.topic}")
	public String topic;

//	@Value("${consumer.api.threadcount}")
	public int threadCount;
//    @Value("${consumer.api.zookeeper.connect}")
    public String zookeeperConnect;
   
    @Value("${consumer.api.auto.connect:true}")
	private boolean consumerAutoConnect;
    
    private ExecutorService executor;
	private List<ConsumeHandler> handlers;

	public KafkaMessageConsumeService() {
		super();
		executor = Executors.newFixedThreadPool(50);
		handlers = new ArrayList<ConsumeHandler>();
	}

	//로컬 테스트를 위해 임시 주석처리
//	@PostConstruct
	public void initialize() {
		
		settingEnv();
		
		if (consumerAutoConnect) {
			logger.debug("init KafkaMessageConsumeService");
			for (int i = 0; i < threadCount; i++) {
				runConsumer(topic);
			}
		}
	}

	private void settingEnv(){
		
		String threadCountStr = System.getenv(Env.CONSUME_THREAD_COUNT);
		if(threadCountStr != null){
			threadCount = Integer.valueOf(threadCountStr);
		}else {
			threadCount = Const.DEFAULT_CONSUME_THREAD_COUNT;
		}
		topic = System.getenv(Env.CONSUME_TOPIC);	
		zookeeperConnect = System.getenv(Env.CONSUME_ZOOKEEPER_CONNECT);
		
		logger.info("settingEnv: threadCount {}, topic {}, zookeeperConnect {}",threadCount, topic, zookeeperConnect);
	}
	@PreDestroy
	public void destroy() {
		for (ConsumeHandler handler : handlers) {
			handler.destroy();
		}
		if (executor != null) {
			executor.shutdownNow();
		}
	}

	public void runConsumer(String topic) {
		
		Properties prop = new Properties();
		prop.put("zookeeper.connect", zookeeperConnect);
		prop.put("group.id", "1"); // server name
//		prop.put("consumer.timeout.ms","-1");
			
		
		ConsumerConfig consumerConfig = new ConsumerConfig(prop);
		ConsumeHandler consumer = new ConsumeHandler();
		consumer.initialize(consumerConfig, topic);

		handlers.add(consumer);
		executor.execute(consumer);
	}
	
	private class ConsumeHandler implements Runnable {
		
		private ConsumerConnector consumer;
		protected List<KafkaStream<byte[], byte[]>> streams;
		
		public void initialize(ConsumerConfig consumerConfig, String topic) {
			this.consumer = Consumer.createJavaConsumerConnector(consumerConfig);
			Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
			topicCountMap.put(topic, 1);
			Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
			this.streams = consumerMap.get(topic);
		}

		public void destroy() {
			if (consumer != null) {
				consumer.shutdown();
			}
		}

		@Override
		public void run() {
			for (final KafkaStream stream : streams) {
				ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
				while (iterator.hasNext()) {
					byte[] message = iterator.next().message();
					try {
						receiveMessage(message);
					} catch (Exception e) {
						System.out.println("errror"+e.toString());
					}
				}
			}
		}
	}

}
