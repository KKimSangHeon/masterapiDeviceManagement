/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.base.exception
 * </PRE>
 * @brief
 * @file UnauthorizedException.java
 * @date 2015. 12. 22. 오전 10:21:23
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 12. 22.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.exception;

import com.kt.iot.base.message.ResponseCode;

/**
 * <PRE>
 *  ClassName UnauthorizedException
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 12. 22. 오전 10:21:23
 * @author kim.seokhun@kt.com
 */

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7977074515633412631L;

	private ResponseCode code;

	public BaseException() {
	}
	
	public BaseException(ResponseCode code) {
		this.code = code;
	}
	
	public BaseException(ResponseCode code, String message) {
		super(message);
		this.code = code;
	}
	
	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}
}
