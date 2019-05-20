/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.base.exception
 * </PRE>
 * @brief
 * @file ExceptionCode.java
 * @date 2015. 12. 23. 오후 4:41:23
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 12. 23.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.message;

public enum ResponseCode {

	OK (200, "OK"),
	INTERNAL_AUTHENTICATION_FAILED (310, "Authentication Failed"),
	INTERNAL_INVALID_MESSAGE_FORMAT (330, "Invalid Message Format"),
	INTERNAL_MANDATORY_PARAMETER_MISSING (340, "Mandatory Parameter Missing"),
	INTERNAL_DUPLICATE_PARAMETER (341, "Duplicate Parameter"),
	INTERNAL_INVALID_PARAMETER (342, "Invalid Parameter"),
	INTERNAL_ASSOCIATED_PARAMETER_NOT_FOUND (345, "Associated Parameter Not Found"),
	INTERNAL_TYPE_MISMATCH (346, "Type Mismatch"),
	
	INTERNAL_SERVER_ERROR (400, "Internal Server Error"),
	INTERNAL_TIMEOUT (411,"Timeout"),
	INTERNAL_UPDATED_MANY (420, "Many Resources is Updated"),
	INTERNAL_UPDATED_NO (421, "No Resource is Updated "),

	EXTERNAL_SERVER_ERROR (500, "External Server Error"),
	EXTERNAL_AUTHENTICATION_FAILED (510, "Authentication Failed"),
	EXTERNAL_NOT_SUPPORTED (520, "Not Supported"),
	EXTERNAL_MANDATORY_PARAMETER_MISSING (540, "Mandatory Parameter Missing"),
	EXTERNAL_DUPLICATE_PARAMETER (541, "Duplicate Parameter"),
	EXTERNAL_INVALID_PARAMETER (542, "Invalid Parameter"),
	EXTERNAL_NOT_FOUND (544, "Not Found"),
	EXTERNAL_NOT_REACHABLE (547, "Invalid Parameter"),
	EXTERNAL_CONNECTION_FAILED (610, "Connection Failed"),
	EXTERNAL_TIMEOUT (611, "Timeout");

	private final int value;
	private final String reasonPhrase;

	private ResponseCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	public static ResponseCode valueOf(int value) {
		for (ResponseCode responseCode : values()) {
			if (responseCode.value == value) {
				return responseCode;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + value + "]");
	}
	
//	public static ResponseCode valueOf(String value) {
//		for (ResponseCode responseCode : values()) {
//			if (responseCode.toString().equals(value)) {
//				return responseCode;
//			}
//		}
//		throw new IllegalArgumentException("No matching constant for [" + value + "]");
//	}

}