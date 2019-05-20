/*
 * GiGA IoT Platform version 2.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */

package com.kt.iot.api.message.vo;

import java.util.HashMap;
import java.util.Map;
/**
 * Kafka를 통해 전파 되는 메시지의 타입
 * @author SanghyunLee
 * @since 2019. 5. 9.
 *
 */
public enum KafkaOperationType {
	
	/** 리소스 생성*/
	CREATE ("00"),
	/** 리소스 수정, 갱신*/
	UPDATE ("01"),
	/** 리소스 삭제*/
	DELETE ("10"),
	/** 리소스 조회*/
	RETRIEVE ("11")
	;

	String value;
	
	private static final Map<String, KafkaOperationType> map = new HashMap<String, KafkaOperationType>();
	static {
		for (KafkaOperationType it : values()) {
			map.put(it.getValue(), it);
		}
	}

	// value에 해당되는 enum을 반환
	public static KafkaOperationType getEnum(String value) {
		return map.get(value);
	}

	KafkaOperationType(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	public boolean isValue(String value){
		return this.value == value;
	}

}
