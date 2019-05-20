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

package com.kt.iot.base.exception;

/**
 *  EC 와의 통신과 나타나는 에러 
 */

public class EcException extends Exception{

	private static final long serialVersionUID = 4380052934599623232L;
	
	public EcException(){
		super();
	}
	
	public EcException(String message){
		super(message);
	}
	
	public EcException(String message, Throwable cause){
		super(message, cause);
	}
	
	public EcException(Throwable cause){
		super(cause);
	}

	 protected EcException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	}
}
