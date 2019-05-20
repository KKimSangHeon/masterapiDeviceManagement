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

package com.kt.iot.base.mvc.context;

import com.kt.iot.base.mvc.message.MessageAccessor;

/**
 * Message 처리를 위해 사용되는 임시 저장소
 * 
 * @author jeado
 *
 */
public class BaseRequestContext {

	private MessageAccessor messageAccessor;
	
	/**
	 * 클라이언트에 전달한 메시지를 반화한다.
	 * 
	 * @return messageAccessor
	 */
	public MessageAccessor getMessageAccessor() {
		return messageAccessor;
	}
	
	/**
	 * 클라이언트에 전달한 메시지를 설정한다.
	 * 
	 * @param messageAccessor
	 */
	public void setMessageAccessor(MessageAccessor messageAccessor) {
		this.messageAccessor = messageAccessor;
	}
}
