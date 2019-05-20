/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.api.v11.member.controller
 * </PRE>
 * @brief
 * @file MemberController.java
 * @date 2015. 11. 4. 오후 3:49:03
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 11. 4.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.api.v2.DeviceRegistraction.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.iot.Const;
import com.kt.iot.Version;
import com.kt.iot.api.v2.DeviceRegistraction.VO.MsDevice;
import com.kt.iot.api.v2.DeviceRegistraction.service.DeviceRegistractionService;
import com.kt.iot.base.common.ResultListVO;
import com.kt.iot.base.exception.BadRequestException;
import com.kt.iot.base.message.ResponseCode;
import com.kt.iot.base.mvc.message.Messages;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <PRE>
 *  ClassName MemberController
 * </PRE>
 * 
 * @brief
 * @version 1.0
 * @date 2015. 11. 4. 오후 3:49:03
 * @author kim.seokhun@kt.com
 */
@RestController
@RequestMapping("/" + Version.V2)
public class DeviceRegistrationController {

	@Autowired
	private DeviceRegistractionService deviceRegistractionService;

	@ApiOperation(value = "장치 등록", notes = "장치를 등록합니다.", httpMethod = "POST", response = MsDevice.class)
	@RequestMapping(value = "/members/{memberSequence}/targets/{targetSequence}/devices", method = POST)
	private MsDevice getMemgers(
			@ApiParam(name = "memberSequence", value = "회원 일련번호", required = true) @PathVariable Long memberSequence,
			@ApiParam(name = "targetSequence", value = "서비스 대상 일련번호", required = true) @PathVariable Long targetSequence,
			@ApiParam(name = "SpotDevBas", value = "SpotDevBas", required = true) @RequestBody MsDevice spotDevBas,
			@ApiIgnore Messages messages,
			@ApiParam(name = "token", value = "token", required = false) @RequestHeader(value = "Authorization") String token,
			@ApiParam(name = "tokenType", value = "tokenType : admin or null", required = false) @RequestHeader(value = "tokenType", required = false) String tokenType) {

		String path = System.getProperty("properties.path");
		System.out.println("path:" + path);

		Long tokenMemberSequence = deviceRegistractionService.parsingAccessTokenToMbrSeq(token,tokenType);
		memberSequence = (tokenMemberSequence != null) ? tokenMemberSequence:memberSequence;
		
		System.out
				.println("member:" + memberSequence + "/target:" + targetSequence + "/Device:" + spotDevBas.toString());
		
		Map<String,String> tokenData= new HashMap<>();
		tokenData.put("Authorization", token);
		tokenData.put("tokenType", tokenType);
		
		MsDevice result = deviceRegistractionService.registerDevice(spotDevBas, memberSequence, targetSequence,tokenData);
		messages.addMessage(Const.RESPONSE_CODE_OK, "");
		return result;
	}

	@ApiOperation(value = "장치 상세 조회", notes = "장치 상세정보를 조회한다.", httpMethod = "GET", response = MsDevice.class)
	@RequestMapping(value = "/externals/{externalId}/devices/{deviceId}/simple", method = GET)
	private MsDevice getDevice(
			@ApiParam(name = "externalId", value = "connection Id", required = true) @PathVariable String externalId,
			@ApiParam(name = "deviceId", value = "디바이스 ID", required = true) @PathVariable String deviceId,
			@ApiIgnore Messages messages,
			@ApiParam(name = "token", value = "token", required = false) @RequestHeader(value = "Authorization") String token,
			@ApiParam(name = "tokenType", value = "tokenType : admin or null", required = false) @RequestHeader(value = "tokenType", required = false) String tokenType) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("externalId", externalId);
		parameter.put("deviceId", deviceId);
		Long memberSequence = deviceRegistractionService.parsingAccessTokenToMbrSeq(token,tokenType);
		parameter.put("memberSequence", memberSequence);
		

		Map<String, String> tokenData = new HashMap<>();
		tokenData.put("Authorization", token);
		tokenData.put("tokenType", tokenType);

		
		MsDevice device = deviceRegistractionService.getDevice(parameter, tokenData);
		messages.addMessage(Const.RESPONSE_CODE_OK, null);
		return device;
	}

}
