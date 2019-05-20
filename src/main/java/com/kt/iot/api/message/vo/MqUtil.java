package com.kt.iot.api.message.vo;

import java.nio.ByteBuffer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kt.iot.api.message.vo.MqCode.EncodingType;
import com.kt.iot.api.message.vo.MqCode.HeaderType;


/**
 * 메세지변환유틸
 * @since	: 2017. 9. 25.
 * @author	: 추병조
 * <PRE>
 * Revision History
 * ----------------------------------------------------
 * 2017. 9. 25. 추병조: 최초작성
 * ----------------------------------------------------
 * </PRE>
 */
public class MqUtil
{
	public static final int PACKET_MIN_LEGNTH = 30;
	public static final int COMMON_HEADER_LEGNTH = 26;
	public static final int COMMON_HEADER_POS = 4;

	/** Gson 유틸틀래스 */
	private static Gson gson = null;

	static
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		gson = gsonBuilder.create();
	}

	public static MqData toMqData(byte[] packet) throws Exception
	{
		if(packet == null)
		{
			throw new Exception("packet is null.");
		}

		if(packet.length < PACKET_MIN_LEGNTH)
		{
			throw new Exception("packet.length error. packet.length = "+packet.length);
		}

		Integer packetLength = ConvertUtil.bytesToint(packet, 0);
		byte[] commonHeaderPacket = new byte[COMMON_HEADER_LEGNTH];
		System.arraycopy(packet, COMMON_HEADER_POS, commonHeaderPacket, 0, COMMON_HEADER_LEGNTH);
		CommonHeader commonHeader = new CommonHeader(commonHeaderPacket);

		int extensionHeaderLength = commonHeader.getHeaderLength();
		byte[] extensionHeaderPacket = new byte[extensionHeaderLength];
		System.arraycopy(packet, COMMON_HEADER_POS+COMMON_HEADER_LEGNTH, extensionHeaderPacket, 0, extensionHeaderLength);
		int payloadPos = COMMON_HEADER_POS + COMMON_HEADER_LEGNTH + extensionHeaderLength;
		int payloadLength = packet.length - payloadPos;
		byte[] payload = new byte[payloadLength];
		System.arraycopy(packet, payloadPos, payload, 0, payloadLength);

		if(commonHeader.getHeaderType() == HeaderType.BASIC)
		{
			if(commonHeader.getHeaderEncodingType() == EncodingType.JSON)
			{
				String extensionHeader = new String(extensionHeaderPacket);
				BasicHeader basicHeader = gson.fromJson(extensionHeader, BasicHeader.class);
				return new MqData(packetLength, commonHeader,  basicHeader, payload);
			}
			else
			{
				throw new Exception("HeaderEncodingType error. HeaderEncodingType = "+commonHeader.getHeaderEncodingType());
			}
		}
		else
		{
			throw new Exception("headerType error. headerType = "+commonHeader.getHeaderType());
		}
	}

	public static byte[] toPacket(Long transactionId, String serviceId, KafkaMsgType messageType, BasicHeader basicHeader, byte[] payload)throws Exception
	{
		String strBasicHeader = gson.toJson(basicHeader);
		byte[] basicHeaderPacket = strBasicHeader.getBytes();
		CommonHeader commonHeader = new CommonHeader(transactionId, serviceId, messageType, (short)basicHeaderPacket.length);
		byte[] commonHeaderPacket = commonHeader.toPacket();

		Integer packetLength = 4 + commonHeaderPacket.length + basicHeaderPacket.length + payload.length;
		byte[] packetLengthPacket = ConvertUtil.intTobytes(packetLength);

		ByteBuffer byteBuffer = ByteBuffer.allocate(packetLength);
		byteBuffer.put(packetLengthPacket);
		byteBuffer.put(commonHeaderPacket);
		byteBuffer.put(basicHeaderPacket);
		byteBuffer.put(payload);

		return byteBuffer.array();
	}

	public static byte[] toPacket(MqData mqData)throws Exception
	{
		String strBasicHeader = gson.toJson(mqData.getBasicHeader());
		byte[] basicHeaderPacket = strBasicHeader.getBytes();
		CommonHeader commonHeader = mqData.getCommonHeader();
		commonHeader.setHeaderLength((short)basicHeaderPacket.length);
		byte[] commonHeaderPacket = commonHeader.toPacket();
		byte[] payload = mqData.getPayload();

		Integer packetLength = commonHeaderPacket.length + basicHeaderPacket.length + payload.length;
		byte[] packetLengthPacket = ConvertUtil.intTobytes(packetLength);

		ByteBuffer byteBuffer = ByteBuffer.allocate(packetLength);
		byteBuffer.put(packetLengthPacket);
		byteBuffer.put(commonHeaderPacket);
		byteBuffer.put(basicHeaderPacket);
		byteBuffer.put(payload);

		return byteBuffer.array();
	}
}
