/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.base.exception
 * </PRE>
 * @brief
 * @file GatewayTimeoutException.java
 * @date 2015. 12. 28. 오전 11:51:14
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 12. 28.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.exception;

/**
 * <PRE>
 *  ClassName GatewayTimeoutException
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 12. 28. 오전 11:51:14
 * @author kim.seokhun@kt.com
 */

public class GatewayTimeoutException extends BaseException {

	private static final long serialVersionUID = -4407124579112894082L;
	
	private Object data;

	public GatewayTimeoutException() {
	}

	public GatewayTimeoutException(String message) {
		super(message);
	}

	public GatewayTimeoutException(Throwable cause) {
		super(cause);
	}

	public GatewayTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
