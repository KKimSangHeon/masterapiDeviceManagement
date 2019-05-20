/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.iot.api.util
 * </PRE>
 * @brief
 * @file StringUtil.java
 * @date 2015. 12. 3. 오후 2:34:55
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2015. 12. 3.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.iot.base.util;

/**
 * <PRE>
 *  ClassName StringUtil
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 12. 3. 오후 2:34:55
 * @author kim.seokhun@kt.com
 */

public class StringUtil {

	private static long RANDOM_SEQ = 0;
	private static int 	LENGTH = 10;

	public synchronized static String getNextSequence(){
		String next = Long.toString(RANDOM_SEQ);
		if(RANDOM_SEQ == (Math.pow(10, LENGTH) - 1)) {
			RANDOM_SEQ = 0;
		} else {
			RANDOM_SEQ++;
		}
		String prefix = "";
		for (int i = 0 ; i < (LENGTH - next.length()) ; i++) {
			prefix += "0";
		}
		next = prefix + next;
		return next;
	}
}
