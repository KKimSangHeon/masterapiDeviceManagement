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

package com.kt.iot.base.mvc.message;

import com.kt.iot.api.Page;

/**
 * 컨트롤러 클래스의 메서드에서 사용되는 메시지 인터페이스.
 * 해당 인터페이스를 통하여 UI에 넘길 메시지를 추가한다.
 * 여러건의 메시지 추가가 가능하다.
 *
 * @author jeado
 *
 */
public interface Messages {

	void addMessage(String code, String msg);

	void addMessage(String code, String msg, Page paging);

}
