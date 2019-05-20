package com.kt.iot.base.exception;

import com.kt.iot.base.message.ResponseCode;

public class BadRequestException extends BaseException {

	private static final long serialVersionUID = 81963875134266622L;

	public BadRequestException() {
	}

	public BadRequestException(ResponseCode code) {
		super(code);
	}
	
	public BadRequestException(ResponseCode code, String message) {
		super(code, message);
	}
	
	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
