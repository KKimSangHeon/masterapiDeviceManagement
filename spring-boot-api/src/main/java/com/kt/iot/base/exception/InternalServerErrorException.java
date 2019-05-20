package com.kt.iot.base.exception;

import com.kt.iot.base.message.ResponseCode;

public class InternalServerErrorException extends BaseException {

	private static final long serialVersionUID = -3899952140046956076L;

	public InternalServerErrorException() {
	}

	public InternalServerErrorException(ResponseCode code) {
		super(code);
	}
	
	public InternalServerErrorException(ResponseCode code, String message) {
		super(code, message);
	}
	
	public InternalServerErrorException(String message) {
		super(message);
	}

	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
