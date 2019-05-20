package com.kt.iot.base.exception;

public class UnprocessableEntityException extends BaseException {

	private static final long serialVersionUID = 81963875134266622L;

	public UnprocessableEntityException() {
	}

	public UnprocessableEntityException(String message) {
		super(message);
	}

	public UnprocessableEntityException(Throwable cause) {
		super(cause);
	}

	public UnprocessableEntityException(String message, Throwable cause) {
		super(message, cause);
	}
}
