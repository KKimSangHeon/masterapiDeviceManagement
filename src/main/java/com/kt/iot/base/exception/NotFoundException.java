package com.kt.iot.base.exception;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = 81963875134266622L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
