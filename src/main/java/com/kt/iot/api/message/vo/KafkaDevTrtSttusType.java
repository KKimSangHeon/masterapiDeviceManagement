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

public enum KafkaDevTrtSttusType {
	/** 성공 */
	SUCCESS( "01" ),
	/** 일반오류 */
	GENERIC_ERROR( "02" ),
	/** 타임아웃 */
	TIMEOUT( "03" ),
	/** 복호화 오류 */
	DECODE_ERROR( "04" ),
	/** 파싱오류 */
	PARSE_ERROR( "05" ),
	/** 인증오류 */
	CERT_ERROR( "06" ),
	/** 응답오류 */
	RES_ERROR( "07" )
	;
	
	String value;

	private static final Map<String, KafkaDevTrtSttusType> map = new HashMap<String, KafkaDevTrtSttusType>();
	static {
		for (KafkaDevTrtSttusType it : values()) {
			map.put(it.getValue(), it);
		}
	}	

	// value에 해당되는 enum을 반환
	public static KafkaDevTrtSttusType fromString(String value) {
		return map.get(value);
	}
	
    KafkaDevTrtSttusType(String value) {
            this.value = value;
    }
    
	public String getValue() {
		return this.value;
	}
    
}
