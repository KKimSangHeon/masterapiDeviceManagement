///**
// * <PRE>
// *  Project 3MP.master-api
// *  Package com.kt.iot.api.v11.member.service
// * </PRE>
// * @brief
// * @file MemberService.java
// * @date 2015. 11. 4. 오후 6:05:19
// * @author kim.seokhun@kt.com
// *  변경이력
// *        이름     : 일자          : 근거자료   : 변경내용
// *       ------------------------------------
// *        kim.seokhun@kt.com  : 2015. 11. 4.       :            : 신규 개발.
// *
// * Copyright © 2013 kt corp. all rights reserved.
// */
package com.kt.iot.api.v2.DeviceRegistraction.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kt.iot.Const;
import com.kt.iot.api.message.service.AbstractMessageConsumeService;
import com.kt.iot.api.message.service.KafkaMessageConsumeService;
import com.kt.iot.api.message.service.MessageProduceService;
import com.kt.iot.api.v2.DeviceRegistraction.VO.DeviceId;
import com.kt.iot.api.v2.DeviceRegistraction.VO.MsDevice;
import com.kt.iot.api.v2.DeviceRegistraction.repository.DeviceModelRepository;
import com.kt.iot.api.v2.DeviceRegistraction.repository.DeviceRepository;
import com.kt.iot.api.v2.DeviceRegistraction.repository.TargetRepository;
import com.kt.iot.base.common.ResultListVO;
import com.kt.iot.base.exception.BadRequestException;
import com.kt.iot.base.message.BaseResponse;
import com.kt.iot.base.message.ResponseCode;
import com.kt.iot.base.util.ObjectConverter;

//import com.kt.iot.base.exception.BadRequestException;
//import com.kt.iot.base.message.ResponseCode;

@Service
public class DeviceRegistractionService {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private TargetRepository targetRepository;

	@Autowired
	private DeviceModelRepository deviceModelRepository;

	@Autowired
	MessageProduceService kafkaMessageProduceService;
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceRegistractionService.class);

	private String acpURL;

	private String contextPath;

	public MsDevice registerDevice(MsDevice device, Long memberSequence, Long targetSequence,
			Map<String, String> tokenData) {

		if (device == null) {
			throw new BadRequestException(ResponseCode.INTERNAL_INVALID_PARAMETER);
		}

		if (!checkACP(device.getTarget().getSequence(), memberSequence, null, tokenData)) {
			throw new BadRequestException(ResponseCode.INTERNAL_AUTHENTICATION_FAILED);
		}

		// 서비스 대상 조회
		// targetSequnce / 삭제유무
		boolean isTargetSequence = targetRepository.exists(device.getTarget().getSequence());

		// 현장장치 모델 조회
		// 일련번호 / 메타정보 / 삭제유무
		boolean isDeviceModel = deviceModelRepository.exists(device.getModel().getSequence());

		// Target , Device Model 유효체크
		if (!(isTargetSequence && isDeviceModel)) {
			throw new BadRequestException(ResponseCode.INTERNAL_INVALID_PARAMETER);
		}

		String delYn = "N";
		MsDevice isDupDevice;
		// device id, connection id이 일치하고 delyn='n' 인것이 존재하나 확인
		isDupDevice = deviceRepository.findBySpotDevIdAndConnectionIdAndDelYn(device.getId(), device.getConnectionId(),
				delYn);

		if (isDupDevice != null) {
			throw new BadRequestException(ResponseCode.INTERNAL_DUPLICATE_PARAMETER);
		}

		// 해당 Target에서 가장 높은 값 조회
		List<MsDevice> equalTargetDevice = deviceRepository
				.findByTargetSequneceOrderBySequenceAsc(device.getTarget().getSequence());

		Long deviceSequence = 1L;
		if (equalTargetDevice.size() != 0) {
			deviceSequence = equalTargetDevice.get(0).getSequence() + 1L;
		}
		device.setSequence(deviceSequence);

		// 디바이스 sequence, target sequnce로 1차 중복체크
		boolean isExistDevice = deviceRepository
				.exists(new DeviceId(device.getSequence(), device.getTarget().getSequence()));

		if (isExistDevice) {
			throw new BadRequestException(ResponseCode.INTERNAL_DUPLICATE_PARAMETER);
		}

		// target,model을 object로 받으려면 어쩔수 없이 거쳐야 한다고 판단
		device.setTargetSequnece(device.getTarget().getSequence());
		device.setModelSequence(device.getModel().getSequence());
		device.setDelYn(delYn);

		// JPA에서 id로 인식하기 때문에 발생하는 문제로 인해 Transient처리 했기때문에 다음 과정을 거침
		device.setSpotDevId(device.getId());
		MsDevice result = deviceRepository.save(device);

		result.setModel(deviceModelRepository.findOne(device.getModel().getSequence()));
		result.setTarget(targetRepository.findOne(device.getTarget().getSequence()));
		
		
		/*kafkaMessageProduceService.resourceCreateMessage(ObjectConverter.toGsonString(result),
				result.getTarget().getDistrictCode() + result.getTarget().getThemeCode()
						+ result.getTarget().getServiceCode(),
				kafkaMessageProduceService.genTransactionKey(), kafkaMessageProduceService.genE2eTransactionId(), null);*/

		return result;
	}

	public MsDevice getDevice(Map<String, Object> param, Map<String, String> tokenData) {
		String spotDevId = (String) param.get("deviceId");
		String connectionId = (String) param.get("externalId");

		String delYn = "N";
		MsDevice device = deviceRepository.findBySpotDevIdAndConnectionIdAndDelYn(spotDevId, connectionId, delYn);

		if (device == null) {
			throw new BadRequestException(ResponseCode.INTERNAL_ASSOCIATED_PARAMETER_NOT_FOUND);
		}

		if (!checkACP(device.getTargetSequnece(), (Long) (param.get("memberSequence")), device.getSequence(),
				tokenData)) {
			throw new BadRequestException(ResponseCode.INTERNAL_AUTHENTICATION_FAILED);
		}

		return device;
	}

	public boolean checkACP(Long targetSequence, Long memberSequence, Long deviceSequence,
			Map<String, String> tokenData) {

//		acpURL = "http://169.56.70.69:32187";
		acpURL = System.getenv("acpHost");

		Map<String, String> param = new HashMap<String, String>();
		param.put("operationType", "retrieve");
		param.put("extrMemberSequence", memberSequence.toString());
		URI requestUri = null;

//		targetSequnece="1004";

		if (deviceSequence == null) {
			// 사용자의 청약에 대한 접근 권한을 확인합니다.
			contextPath = "/accessControl/v1/target/";
			requestUri = getUrl(acpURL + contextPath, targetSequence.toString(), param);
		} else {
			// 사용자의 장치에 대한 접근 권한을 확인합니다.
			contextPath = "/accessControl/v1/target/" + targetSequence.toString() + "/device/";
			requestUri = getUrl(acpURL + contextPath, deviceSequence.toString(), param);
		}

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", tokenData.get("Authorization"));
		headers.set("tokenType", tokenData.get("tokenType"));

		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<BaseResponse> response = restTemplate.exchange(requestUri, HttpMethod.GET, entity,
				BaseResponse.class);

		boolean hasACP = (boolean) ((LinkedHashMap<String, Object>) response.getBody().getData()).get("result");

		return hasACP;
	}

	public Long parsingAccessTokenToMbrSeq(String token, String tokenType) {
		Long memberSequence = null;
		if (token != null) {
			if (Const.TOKEN_TYPE_ADMIN.equals(tokenType) && Const.TOKEN_ADMIN.equals(token)) {
				// admin token 확인 로직 개선 필요
			} else {
				token = token.replaceAll("Bearer ", "");
				JSONObject object = parsingToken(token);
				if (object != null) {
					memberSequence = Long.valueOf((String) object.get("mbr_seq"));
				}
			}
		} else {
			throw new BadRequestException(ResponseCode.INTERNAL_AUTHENTICATION_FAILED);
		}

		return memberSequence;
	}

	public JSONObject parsingToken(String token) {

		if (token == null)
			return null;
		Jwt jwt = JwtHelper.decode(token);
		JSONObject jsonObject = null;
		try {
			jsonObject = ObjectConverter.toJSON(jwt.getClaims());

		} catch (JsonProcessingException | ParseException e) {
			// TODO Auto-generated catch block
		}
		return jsonObject;
	}

	protected URI getUrl(String baseUrl, String path) {
		return getUrl(baseUrl, path, null);
	}

	protected URI getUrl(String baseUrl, String path, Map<String, String> param) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl).path(path);
		if (param != null) {
			Iterator<String> iteratortor = param.keySet().iterator();
			while (iteratortor.hasNext()) {
				String key = (String) iteratortor.next();
				builder.queryParam(key, param.get(key));
			}
		}
		return builder.build().toUri();
	}

	protected <T> HttpEntity<T> getRequestEntity(T body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<T>(body, headers);
	}
}
