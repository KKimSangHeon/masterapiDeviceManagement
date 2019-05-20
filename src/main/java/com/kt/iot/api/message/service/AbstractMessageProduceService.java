package com.kt.iot.api.message.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.kt.iot.Const;
import com.kt.iot.api.message.vo.BasicHeader;
import com.kt.iot.api.message.vo.KafkaMsgType;
import com.kt.iot.api.message.vo.MqUtil;
import com.kt.iot.api.message.vo.KafkaOperationType;
import com.kt.iot.base.util.StringUtil;

public abstract class AbstractMessageProduceService implements MessageProduceService {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMessageProduceService.class);

//	private MessageConsumeService messageConsumeService;
	
	@Value("${instance.name:apiName}")
    protected String serverName;
	@Value("${instance.id:apiId}")
    protected String serverId;


	protected byte[] toMessage(List<String> targetModules, List<String> targetInstanceIds, KafkaMsgType messageType, String messageBody, String serviceCode, String transactionKey, String e2eTransactionId, Map<String,String> extensions) throws Exception {
			
			List<String> to = targetModules; 
			if(targetModules == null)
				to = new ArrayList<String>();
//			to.add("EC");
			
			List<String> toInstance = targetInstanceIds; 
			if(toInstance == null)
				toInstance = new ArrayList<String>();
//			toInstance.add(targetInstanceId);
			
			BasicHeader basicHeader = null;
			if (e2eTransactionId == null) {
				basicHeader = new BasicHeader(serverName, serverId, serverId + System.currentTimeMillis()); //e2eTransactionId : serverId + System.currentTimeMillis()
			} else {
				basicHeader = new BasicHeader(serverName, serverId, e2eTransactionId);
			}
			basicHeader.setTo(to);
			basicHeader.setToInstance(toInstance);
			
			/*
			 * 메시지의 속성 및 관련 필요한 정보 넣음
			 */
			if(extensions != null){
				basicHeader.setExtensions(extensions);
			}
			
			byte[] payload = messageBody.getBytes();
			byte[] packet = MqUtil.toPacket(System.currentTimeMillis(), serviceCode, messageType, basicHeader, payload);
			
			StringBuilder log = new StringBuilder();
			log.append("*************************Send JSON Message*******************\n");
			log.append("header ::{contentType:"+basicHeader.getContentType()+",from:"+basicHeader.getFrom()+",fromInstance:"+basicHeader.getFromInstance()+",to: "+basicHeader.getTo().toString()+",resourceUri(Group Tag): "+basicHeader.getResourceUri()
			+",e2eTransactionId:"+basicHeader.getE2eTransactionId()+",resultCode:"+basicHeader.getResultCode()+",messageResultCode:"+basicHeader.getMessageResultCode()+",extensions:");
					if(basicHeader.getExtensions()!=null)
						log.append(basicHeader.getExtensions().toString()+"} ");
					else log.append("null} ");							
			log.append("\nbody ::" + messageBody);
			log.append("\n*************************************************************");
			logger.info(log.toString());
			
			return packet;
		
	}
	
	/**
	 * byte array message를 MQ에 publish
	 * @since 2019. 5. 9.
	 * @author SanghyunLee
	 * @param message
	 */
	public abstract void sendMessage(byte[] message);
	
	private List<String> settingDestination(){
		
		List<String> destinations = new ArrayList<>();
		destinations.add("deviceAccesscontrol");
		destinations.add("deviceControl");
		destinations.add("EC");
		
		return destinations;
	}
	
	private List<String> settingTargetInstance(){
		
		List<String> targetInstance = new ArrayList<>();
		targetInstance.add("ALL");
		return targetInstance;
	}
	/** producer - consumer 간 메시지의 Id를 생성 */
	public String genTransactionKey(){

			long time = System.currentTimeMillis();
			String transactionKey = serverId  + Const.KEY_DELIMITER +
			   		StringUtil.getNextSequence() + Const.KEY_DELIMITER +
			   		Long.toString(time);
			return transactionKey;
	}

	public String genE2eTransactionId(){

		long time = System.currentTimeMillis();
		String transactionKey = Const.PREFIX_E2E_ID + Const.KEY_DELIMITER 
								+ serverId  + Const.KEY_DELIMITER +
		   		StringUtil.getNextSequence() + Const.KEY_DELIMITER +
		   		Long.toString(time);
		return transactionKey;
	}
	/** kafka 메시지의 OperationType을 셋팅 */
	protected void settingOperationType(Map<String,String> extensions, KafkaOperationType type){
		
		String key = Const.KAFKA_OPERATION_TYPE;
		String value = type.getValue();
		extensions.put(key, value);
		
	}
	
	public void sendMessage(String message,KafkaMsgType kafkaMsgType,String sericeCode,String transactionKey,String e2eTransactionId, Map<String,String> extensions){
		
		byte[] packet = null;
		
		try {
			packet = toMessage(settingDestination(), settingTargetInstance(), kafkaMsgType, message, sericeCode, transactionKey, e2eTransactionId, extensions);
			
			sendMessage(packet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
	}
}
