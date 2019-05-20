/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.base.exception
 * </PRE>
 * @brief
 * @file UnauthorizedException.java
 * @date 2015. 12. 22. 오후 1:41:21
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 12. 22.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.exception;

/**
 * <PRE>
 *  ClassName UnauthorizedException
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 12. 22. 오후 1:41:21
 * @author kim.seokhun@kt.com
 */

public class UnauthorizedException extends BaseException {

	private static final long serialVersionUID = 7977074515633412631L;

	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
}
