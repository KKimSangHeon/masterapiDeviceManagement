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

package com.kt.iot;

/**
 * Const
 * @author Sangsun Park (blue.park@kt.com)
 *
 */
public class Const {

	public static final String PACKAGE = "com.kt.iot";

	public static final String DELIMITER = "-7b75C433-";
	public static final String KEY_DELIMITER = "#";
	
	public static final String MESSAGE_QUEUE_TYPE_KAFKA 	= "KAFKA";
	public static final String MESSAGE_QUEUE_TYPE_RABBIT 	= "RABBIT";
	
	public static final String MESSAGE_VERSION_2 = "2";
	public static final String MESSAGE_VERSION_3 = "3";

	public static final String RESPONSE_TYPE_SYNC 	= "SYNC";
	public static final String RESPONSE_TYPE_ASYNC 	= "ASYNC";

	public static final String RESPONSE_CODE_OK		= "200";
	
	public static final String LOG_TYPE_CLCT		= "0001";
	public static final String LOG_TYPE_CTRL		= "0002";
	public static final String LOG_TYPE_DEV			= "0003";

	public static final String EXT_REQ_TYPE_CONT	= "CNTL";
	public static final String EXT_REQ_TYPE_CONF	= "CONF";
	public static final String EXT_REQ_TYPE_INFO	= "EXTE";
	public static final String EXT_REQ_TYPE_FWRE	= "FIRM";
	public static final String EXT_REQ_TYPE_STAT	= "QURY";
	public static final String EXT_REQ_TYPE_FLTR	= "FLTR";

	public static final String TAG_STRM_VAL_TYPE_NUM	= "0000010";
	public static final String TAG_STRM_VAL_TYPE_TXT	= "0000020";

	public static final String FILE_TYPE_MODEL_IMG		= "1001";
	public static final String FILE_TYPE_DEVICE_IMG		= "1002";
	public static final String FILE_TYPE_AUTH_CERT		= "2001";
	public static final String FILE_TYPE_AUTH_CONF		= "2002";
	public static final String FILE_TYPE_VERIFY_CERT	= "2003";
	public static final String FILE_TYPE_VERIFY_CONF	= "2004";

	public static final String DISCONNECT = "DISCONNECT";
	
	public enum EndpointType {
		DEVICE_RESOURCES,
		DEVICE_RESOURCE,
		SYSTEM_RESOURCES,
		SYSTEM_RESOURCE;
	}
}
