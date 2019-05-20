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

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.iot.Const;
import com.kt.iot.Version;
import com.kt.iot.api.VO.Device.Device;
import com.kt.iot.api.v2.DeviceRegistraction.service.DeviceRegistractionService;
import com.kt.iot.base.mvc.message.Messages;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <PRE>
 *  ClassName MemberController
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 11. 4. 오후 3:49:03
 * @author kim.seokhun@kt.com
 */
@RestController
@RequestMapping("/" + Version.V2+"/members/{memberSequence}/targets/{targetSequence}/devices")
public class DeviceRegistrationController {

	@Autowired
	private DeviceRegistractionService deviceRegistractionService;

	@ApiOperation(value = "장치 등록", notes = "장치를 등록합니다.", httpMethod = "POST", response = Device.class)
    @RequestMapping(value="", method=POST)
	private Device getMemgers(@ApiParam(name="memberSequence", value="회원 일련번호", required=true)
							 	 	@PathVariable Long memberSequence,
							 	 	@ApiParam(name="targetSequence", value="서비스 대상 일련번호", required=true)
							 	 	@PathVariable Long targetSequence,
							 	 	@ApiParam(name = "Device", value = "Device", required = true) @RequestBody Device device,
							 	 	@ApiIgnore
 	 								Messages messages) {

		  String  path = System.getProperty("properties.path");
	        System.out.println("path:"+path);

//		messages.addMessage(Const.RESPONSE_CODE_OK, "",new Page(memberService.getMemberCount(parameter), offset, limit));
		System.out.println("member:"+memberSequence +"/target:"+targetSequence+"/Device:"+device.toString());
		Device result = deviceRegistractionService.registerDevice(device, memberSequence, targetSequence);
		messages.addMessage(Const.RESPONSE_CODE_OK, "");
		return result;
	}
	
	
}
