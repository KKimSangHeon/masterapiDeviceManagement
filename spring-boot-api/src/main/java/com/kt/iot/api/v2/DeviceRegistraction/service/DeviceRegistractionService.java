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

import org.springframework.stereotype.Service;

import com.kt.iot.api.VO.Contract.Contract;
import com.kt.iot.api.VO.Creator.Creator;
import com.kt.iot.api.VO.Device.Device;
import com.kt.iot.api.VO.Model.Model;
import com.kt.iot.base.exception.BadRequestException;
import com.kt.iot.base.message.ResponseCode;

//import com.kt.iot.base.exception.BadRequestException;
//import com.kt.iot.base.message.ResponseCode;

@Service
public class DeviceRegistractionService {

	public Device registerDevice(Device device, Long memberSequence, Long targetSequence) {

		if (device != null) {

			if (memberSequence != null) {
				device.setCreator(new Creator(memberSequence));
			}
			if (targetSequence != null) {
				device.setContract(new Contract(targetSequence));
			}

			Contract contract = device.getContract();
			Model model = device.getModel();

			if (validTarget(contract) && validModel(model)) {

				if (!checkDuplicatedDevice(device)) {

					System.out.println("publish to MessageQueue...." + device.toString());
					return insertDevice(device);
				} else {
					throw new BadRequestException(ResponseCode.INTERNAL_DUPLICATE_PARAMETER);
				}

			} else {
				// 정보가 잘못되었음
				throw new BadRequestException(ResponseCode.INTERNAL_INVALID_PARAMETER);
			}

		} else {
			throw new BadRequestException(ResponseCode.INTERNAL_INVALID_PARAMETER);
		}

	}

	public Boolean validTarget(Contract contract) {
		System.out.println("validateTarget");
		if (contract != null)
			return true;
		else
			return false;

	}

	public Boolean validModel(Model model) {
		System.out.println("validateModel");
		if (model != null)
			return true;
		else
			return false;
	}

	public Boolean checkDuplicatedDevice(Device device) {
		System.out.println("checkDuplicatedDevice");
		return false;
	}

	public Device insertDevice(Device device) {
		System.out.println("insert " + device + " into DataBase...");
		return device;
	}
}
