/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.api.v11
 * </PRE>
 * @brief
 * @file Page.java
 * @date 2015. 11. 4. 오후 6:44:56
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 11. 4.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

/**
 * <PRE>
 *  ClassName Page
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 11. 4. 오후 6:44:56
 * @author kim.seokhun@kt.com
 */
@JsonInclude(Include.NON_EMPTY)
@ApiModel(value = "Page", description = "Page resource representation" )
public class Page {

	@JsonIgnore
	private int total;
	@JsonIgnore
	private int offset;
	@JsonIgnore
	private int limit;

	public Page(int total, int offset, int limit) {
		this.total = total;
		this.offset = offset;
		this.limit = limit;
	}

	@JsonIgnore
	public int getTotal() {
		return total;
	}

	@JsonIgnore
	public void setTotal(int total) {
		this.total = total;
	}

	@JsonIgnore
	public int getOffset() {
		return offset;
	}

	@JsonIgnore
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@JsonIgnore
	public int getLimit() {
		return limit;
	}

	@JsonIgnore
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
