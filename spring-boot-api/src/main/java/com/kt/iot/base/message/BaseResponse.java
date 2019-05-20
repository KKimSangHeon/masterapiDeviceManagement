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

/**
 *
 */
package com.kt.iot.base.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kt.iot.api.Page;

import io.swagger.annotations.ApiModel;

/**
 * @author Sangsun Park (blue.park@kt.com)
 *
 */
@JsonInclude(Include.NON_EMPTY)
@ApiModel(value = "Response", description = "Response resource representation" )
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 5468480346176063191L;

	private String responseCode;
	private String message;
	private Page paging;
    private Object data;

    public BaseResponse() {
        message = "";
        this.setResponseNG();
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode code) {
    	setResponseCode(code.toString());
    	setMessage(code.getReasonPhrase());
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Page getPaging() {
		return paging;
	}

	public void setPaging(Page paging) {
		this.paging = paging;
	}

	public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void putData(Object object) {
        this.data = object;
    }

    public void setResponseOK() {
        this.responseCode = "OK";
    }

    public void setResponseNG() {
        this.responseCode = "NG";
    }

}
