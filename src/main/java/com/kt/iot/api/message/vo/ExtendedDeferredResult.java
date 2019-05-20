package com.kt.iot.api.message.vo;

import java.util.Map;

import org.springframework.web.context.request.async.DeferredResult;

public class ExtendedDeferredResult<T> extends DeferredResult<T> {
	
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
