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

public enum KafkaEncdngType {
	/** userDefined */
	USER_DEFINED( (byte)0x1 ),
	/** xml */
	XML( (byte)0x2 ),
	/** json */
	JSON( (byte)0x3 ),
	/** gpb */
	GPB( (byte)0x10 ),
	/** thrift */
	THRIFT( (byte)0x11 ),
	/** avro */
	AVRO( (byte)0x12 ),
	/** pcre */
	PCRE( (byte)0x13 )
	;
	
	byte value;

	private static final Map<Byte, KafkaEncdngType> map = new HashMap<Byte, KafkaEncdngType>();
	static {
		for (KafkaEncdngType it : values()) {
			map.put(it.getValue(), it);
		}
	}	

	// value에 해당되는 enum을 반환
	public static KafkaEncdngType fromByte(byte value) {
		return map.get(value);
	}
	
    KafkaEncdngType(byte value) {
            this.value = value;
    }
    
	public byte getValue() {
		return this.value;
	}
    
}
