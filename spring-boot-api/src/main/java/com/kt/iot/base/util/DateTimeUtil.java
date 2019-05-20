/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.api.util
 * </PRE>
 * @brief
 * @file DateTimeUtil.java
 * @date 2015. 11. 25. 오후 1:26:23
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 11. 25.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <PRE>
 *  ClassName DateTimeUtil
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 11. 25. 오후 1:26:23
 * @author kim.seokhun@kt.com
 */

public class DateTimeUtil {

	public static Date parse(String time) {
		return parse("yyyy-MM-dd HH:mm:ss.SSS", time);
	}

	public static Date parse(String pattern, String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toString(Date date) {
		return toString(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	public static String toString(Date date, String format) {
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		return transFormat.format(date);
	}
}
