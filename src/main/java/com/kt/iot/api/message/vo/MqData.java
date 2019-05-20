package com.kt.iot.api.message.vo;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * TCP 헤더
 * @since	: 2014. 12. 4.
 * @author	: CBJ
 * <PRE>
 * Revision History
 * ----------------------------------------------------
 * 2014. 12. 4. CBJ: 최초작성
 * ----------------------------------------------------
 * </PRE>
 */
public class MqData implements Serializable, Cloneable
{
	/** 직렬화아이디 */
	private static final long serialVersionUID = -9085676004141026617L;

	/** 전체패킷길이 */
	protected Integer packetLength;
	/** commonHeader */
	protected CommonHeader commonHeader;
	/** basicHeader */
	protected BasicHeader basicHeader;
	/** payload */
	protected byte[] payload;

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public MqData(Integer packetLength, CommonHeader commonHeader, BasicHeader basicHeader, byte[] payload)
	{
		this.packetLength = packetLength;
		this.commonHeader = commonHeader;
		this.basicHeader = basicHeader;
		this.payload = payload;
	}

	public Integer getPacketLength() {
		return packetLength;
	}

	public void setPacketLength(Integer packetLength) {
		this.packetLength = packetLength;
	}

	public CommonHeader getCommonHeader() {
		return commonHeader;
	}

	public void setCommonHeader(CommonHeader commonHeader) {
		this.commonHeader = commonHeader;
	}

	public BasicHeader getBasicHeader() {
		return basicHeader;
	}

	public void setBasicHeader(BasicHeader basicHeader) {
		this.basicHeader = basicHeader;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}


}
