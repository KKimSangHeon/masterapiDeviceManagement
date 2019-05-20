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

import java.util.ArrayList;
import java.util.List;

import com.kt.iot.api.Page;

public class MessagesJsonImpl implements Messages, MessageAccessor{

	private List<Message> messages = new ArrayList<Message>();

	@Override
	public void addMessage(String code, String msg) {
		addMessage(code, msg, null);
	}

	@Override
	public void addMessage(String code, String msg, Page paging) {
		messages.add(new Message(code, msg, paging));
	}

	@Override
	public List<Message> getMessageList() {
		return messages;
	}

}
