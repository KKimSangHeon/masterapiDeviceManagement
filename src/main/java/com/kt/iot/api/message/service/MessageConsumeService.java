package com.kt.iot.api.message.service;

public interface MessageConsumeService {

	void receiveMessage(byte[] message);
	
}
