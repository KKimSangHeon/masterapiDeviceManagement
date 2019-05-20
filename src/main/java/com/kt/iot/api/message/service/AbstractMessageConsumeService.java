package com.kt.iot.api.message.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.kt.iot.Const;
import com.kt.iot.api.VO.Device.Device;
import com.kt.iot.api.message.vo.BasicHeader;
import com.kt.iot.api.message.vo.MqData;
import com.kt.iot.api.message.vo.MqUtil;
import com.kt.iot.api.v2.DeviceRegistraction.VO.Target;
import com.kt.iot.api.v2.DeviceRegistraction.repository.TargetRepository;
import com.kt.iot.base.util.ObjectConverter;
public class AbstractMessageConsumeService implements MessageConsumeService {

	private static final Logger logger = LoggerFactory.getLogger(AbstractMessageConsumeService.class);
	
	
	@Value("${instance.name}")
    protected String serverName;
	@Value("${instance.id}")
    protected String serverId;
	
	@Autowired
	TargetRepository targetRepository;
	
	public void receiveMessage(byte[] message, String topicName) {
		logger.info("in receiveMessage");
		parsing(message, topicName);
	}
	
	protected void parsing(byte[] packet, String topicName){
		try {
			MqData message = MqUtil.toMqData(packet);
			String json = new String(message.getPayload(), "UTF-8");
			StringBuilder log = new StringBuilder();
				BasicHeader basicHeader = message.getBasicHeader();
				log.append("*************** Recieved Message *******************\n");
				log.append(message.getCommonHeader());
				if(basicHeader!= null){
				log.append("header ::{contentType:"+basicHeader.getContentType()+",from:"+basicHeader.getFrom()+",fromInstance:"+basicHeader.getFromInstance());
				if(basicHeader.getTo()!=null)
					log.append(",to: "+basicHeader.getTo().toString());
				else log.append(",to: null");
				log.append(",resourceUri(Group Tag): "+basicHeader.getResourceUri()+",e2eTransactionId:"+basicHeader.getE2eTransactionId()+",resultCode:"+basicHeader.getResultCode()+",messageResultCode:"+basicHeader.getMessageResultCode()+",extensions:");
						if(basicHeader.getExtensions()!=null)
							log.append(basicHeader.getExtensions().toString()+"} ");
						else log.append("null} ");
				}
				log.append("\nbody ::" + json);
				log.append("****************************************************");
				logger.info(log.toString());
//				return parseMessage(message.getCommonHeader().getMessageType(), json);
				
				Map<String, String> extensions = basicHeader.getExtensions();
				if(extensions.containsKey(Const.KAFKA_OPERATION_TYPE)){
					String type = extensions.get(Const.KAFKA_OPERATION_TYPE);
					logger.info("KAFKA_OPERATION_TYPE:{}",type);
//					/** 리소스 생성*/
//					CREATE ("00"),
//					/** 리소스 수정, 갱신*/
//					UPDATE ("01"),
//					/** 리소스 삭제*/
//					DELETE ("10"),
//					/** 리소스 조회*/
//					RETRIEVE ("11")
//					;
				}
				if(extensions.containsKey(Const.KAFKA_OPERATION_OWNER)){
					String type = extensions.get(Const.KAFKA_OPERATION_OWNER);
					logger.info("KAFKA_OPERATION_OWNER:{}",type);
				}
				if(topicName.equals("test")){
					Device device = ObjectConverter.toObject(json,Device.class);
					logger.info("converted object:"+device.toString());
					//장치가 생성되면
					
				}else if(topicName.equals("RESOURCE_TARGET")){
					Target target = ObjectConverter.toObject(json, Target.class);
					logger.info("converted object:"+target.toString());
					
					//청약이 생성되면
					//청약을 acp에 등록해야 함
					// resourceUrl : target/1005
					//originator : KAFKA_OPERATION_OWNER
					//operation : ALL
					// 먼저 ACP 있나 확인 후 있으면 그걸 넣고, 없으면 ACP 생성한다.
					String resourceUrl = "/target/"+target.getSequence();
					System.out.println(resourceUrl);
					Long memberSequence  = Long.valueOf(extensions.get(Const.KAFKA_OPERATION_OWNER));
//					deviceAccesscontrolService.registerResource(memberSequence,resourceUrl,Target.class);
					target.setCreator(memberSequence.toString());
					targetRepository.save(target);
				}
			//}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void receiveMessage(byte[] message) {
		// TODO Auto-generated method stub
		
	}
}

