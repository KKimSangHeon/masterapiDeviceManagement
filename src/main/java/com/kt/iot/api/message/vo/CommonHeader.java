package com.kt.iot.api.message.vo;

import java.io.Serializable;
import java.nio.ByteBuffer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.kt.iot.api.message.vo.MqCode.EncodingType;
import com.kt.iot.api.message.vo.MqCode.EncryptionType;
import com.kt.iot.api.message.vo.MqCode.HeaderType;


/**
 * 공통헤더
 * @since	: 2017. 9. 25.
 * @author	: 추병조
 * <PRE>
 * Revision History
 * ----------------------------------------------------
 * 2017. 9. 25. 추병조: 최초작성
 * ----------------------------------------------------
 * </PRE>
 */
public class CommonHeader implements Serializable, Cloneable
{
	/** 직렬화아이디 */
	private static final long serialVersionUID = -2535554258939408975L;

	public static final int COMMON_HEADER_LEGNTH = 26;

	private static byte byte0x0F =  (byte)0x0F;
//	private static byte byte0x03 =  (byte)0x03;
//	private static byte byte0x01 =  (byte)0x01;
//	private static byte byte0x7F =  (byte)0x7F;

	/** 메인버전 */
	protected byte mainVer = 0x03;
	/** 서브버전 */
	protected byte subVer = 0x00;
	/** 헤더타입 */
	protected HeaderType headerType = HeaderType.BASIC;
	/** 헤더길이 */
	protected Short headerLength = 0;
	/** Transaction ID */
	protected Long transactionId;
	/** Service ID */
	protected String serviceId;
	/** 메세지타입 */
	protected KafkaMsgType messageType;

	/** 헤더인코딩타입 */
	protected EncodingType headerEncodingType = EncodingType.JSON;
	/** payload인코딩타입 */
	protected EncodingType payloadEncodingType = EncodingType.JSON;
	/** payload암호화타입 */
	protected EncryptionType payloadEncryptionType = EncryptionType.PLAIN_TEXT;


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

	public CommonHeader(Long transactionId, String serviceId, KafkaMsgType messageType, Short headerLength, HeaderType headerType, byte mainVer, byte subVer) throws Exception
	{
		this.transactionId = transactionId;
		this.serviceId = serviceId;
		this.messageType = messageType;
		this.headerLength = headerLength;
		this.headerType = headerType;
		this.mainVer = mainVer;
		this.subVer = subVer;
	}

	public CommonHeader(Long transactionId, String serviceId, KafkaMsgType messageType, Short headerLength) throws Exception
	{
		this.transactionId = transactionId;
		this.serviceId = serviceId;
		this.messageType = messageType;
		this.headerLength = headerLength;
	}

	public CommonHeader(Long transactionId, String serviceId, KafkaMsgType messageType) throws Exception
	{
		this.transactionId = transactionId;
		this.serviceId = serviceId;
		this.messageType = messageType;
	}

	public CommonHeader(byte[] packet) throws Exception
	{
		//1. 버전(0)
		this.mainVer = packet[0];
		this.mainVer = (byte) ((mainVer>>4));
		this.mainVer = (byte)(mainVer & byte0x0F);

		this.subVer = packet[0];
		this.subVer = (byte) ((subVer<<4));
		this.subVer = (byte) ((subVer>>4));
		this.subVer = (byte)(subVer & byte0x0F);

		//2. 헤더타입입력(1)
		this.headerType = HeaderType.fromByte(packet[1]);

		//3. 헤더길이(2-2)
		this.headerLength =ConvertUtil.bytesToshort(packet, 2);

		//4. 전송트랜잭션ID입력(4-8)
		this.transactionId = ConvertUtil.bytesTolong(packet, 4);

		//5. serviceId(12-9)
		this.serviceId = ConvertUtil.bytesToString(packet, 9, 12);

		//6. Message Type(21-2)
		Short nMessageType = ConvertUtil.bytesToshort(packet, 21);
		this.messageType = KafkaMsgType.fromShort(nMessageType);

		//7. Header Encoding Type(23)
		this.headerEncodingType = EncodingType.fromByte(packet[23]);
		//8. Payload Encoding Type(24)
		this.payloadEncodingType = EncodingType.fromByte(packet[24]);
		//9. Payload EncryptionType Type(25s)
		this.payloadEncryptionType = EncryptionType.fromByte(packet[25]);
	}

	public byte[] toPacket() throws Exception
	{
		ByteBuffer byteBuffer = ByteBuffer.allocate(COMMON_HEADER_LEGNTH);

		//1. 버전입력
		// Check Parameter Limitations
		if ( mainVer > 0x0F )
		{
			Exception exception = new Exception("Invalid Main Version. main version = " + this.mainVer);
			throw exception;
		}
		if ( subVer > 0x0F )
		{
			Exception exception = new Exception("Invalid Sub Version. main version = " + this.subVer);
			throw exception;
		}
		// Combine mainVer & subVer
		byte version = ((byte) ((mainVer<<4) | subVer));
		byteBuffer.put(version);

		//2. 헤더타입입력(1)
		byteBuffer.put(headerType.getValue());

		//3. 헤더길이(2-2)
		if(headerLength == null)
		{
			byteBuffer.putShort((short)0);
		}
		else
		{
			byteBuffer.putShort(headerLength);
		}

		//4. 전송트랜잭션ID입력(4-8)
		byteBuffer.putLong(this.transactionId);
		//5. serviceId(12-9)
		byte[] arrServiceId = null;
		if(serviceId == null)
		{
			arrServiceId = new byte[9];
		}
		else
		{
			arrServiceId = serviceId.getBytes();
			if(arrServiceId.length != 9 )
			{
				arrServiceId = new byte[9];
			}
		}
		byteBuffer.put(arrServiceId);

		//6. Message Type(21-2)
		byteBuffer.putShort(this.messageType.getValue());
		//7. Header Encoding Type(23)
		byteBuffer.put(this.headerEncodingType.getValue());
		//8. Payload Encoding Type(24)
		byteBuffer.put(this.payloadEncodingType.getValue());
		//9. Payload EncryptionType Type(25)
		byteBuffer.put(this.payloadEncryptionType.getValue());

		return byteBuffer.array();
	}

	public byte getMainVer() {
		return mainVer;
	}

	public void setMainVer(byte mainVer) {
		this.mainVer = mainVer;
	}

	public byte getSubVer() {
		return subVer;
	}

	public void setSubVer(byte subVer) {
		this.subVer = subVer;
	}

	public HeaderType getHeaderType() {
		return headerType;
	}

	public void setHeaderType(HeaderType headerType) {
		this.headerType = headerType;
	}

	public Short getHeaderLength() {
		return headerLength;
	}

	public void setHeaderLength(Short headerLength) {
		this.headerLength = headerLength;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public KafkaMsgType getMessageType() {
		return messageType;
	}

	public void setMessageType(KafkaMsgType messageType) {
		this.messageType = messageType;
	}

	public EncodingType getHeaderEncodingType() {
		return headerEncodingType;
	}

	public void setHeaderEncodingType(EncodingType headerEncodingType) {
		this.headerEncodingType = headerEncodingType;
	}

	public EncodingType getPayloadEncodingType() {
		return payloadEncodingType;
	}

	public void setPayloadEncodingType(EncodingType payloadEncodingType) {
		this.payloadEncodingType = payloadEncodingType;
	}

	public EncryptionType getPayloadEncryptionType() {
		return payloadEncryptionType;
	}

	public void setPayloadEncryptionType(EncryptionType payloadEncryptionType) {
		this.payloadEncryptionType = payloadEncryptionType;
	}
}
