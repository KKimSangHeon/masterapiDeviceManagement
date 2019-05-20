/**
 * <PRE>
 *  Project : GWCommAgent
 *  Package : com.kt.smcp.gw.ca.util
 * </PRE>
 * @file   : ConvertUtil.java
 * @date   : 2013. 12. 20. 오후 6:00:23
 * @author : 추병조
 * @brief  :
 *  변경이력    :
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        추병조  : 2013. 12. 20.       :            : 신규 개발.
 */
package com.kt.iot.api.message.vo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;

import com.google.gson.Gson;
/**
 * <PRE>
 *  ClassName : ConvertUtil
 * </PRE>
 * @version : 1.0
 * @date    : 2013. 12. 20. 오후 6:00:23
 * @author  : 추병조
 * @brief   :
 */

public class ConvertUtil
{
	public static ObjectMapper mapper = null;

	static
	{
		CustomObjectSerializer c = new CustomObjectSerializer();
		mapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule("SimpleModule", new Version(1,0,0,null));
		simpleModule.addSerializer(Enum.class, new CustomObjectSerializer());
		mapper.registerModule(simpleModule);
    }

	public static class CustomObjectSerializer extends JsonSerializer<Enum>
	{
        @Override
        public void serialize(Enum enumVal, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,JsonProcessingException {
            jgen.writeString(enumVal.toString());
        }
    }

	public static <T> T toObject(String json, Class<T> type) throws Exception
	{
		return mapper.readValue(json, type);
		//return gson.fromJson(json, type);
	}

	public static String toString(Object object) throws Exception
	{
		return mapper.writeValueAsString(object);
		//return gson.toJson(object);
	}

	public static short bytesToshort(byte[] value)
	{
		return ByteBuffer.wrap(value).getShort();
	}

	public static short bytesToshort(byte[] value, int index)
	{
		return ByteBuffer.wrap(value, index, 2).getShort();
	}

	public static short bytesToshort(byte[] value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getShort();
		}
		else
		{
			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getShort();
		}
	}

	public static int bytesToint(byte[] value)
	{
		return ByteBuffer.wrap(value).getInt();
	}

	public static int bytesToint(byte[] value, int index)
	{
		return ByteBuffer.wrap(value, index, 4).getInt();
	}

	public static int bytesToint(byte[] value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getInt();
		}
		else
		{
			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getInt();
		}
	}

	public static long bytesTolong(byte[] value)
	{
		return ByteBuffer.wrap(value).getLong();
	}

	public static long bytesTolong(byte[] value, int index)
	{
		return ByteBuffer.wrap(value, index, 8).getLong();
	}

	public static float bytesTofloat(byte[] value)
	{
		return ByteBuffer.wrap(value).getFloat();
	}

	public static float bytesTofloat(byte[] value, int index)
	{
		return ByteBuffer.wrap(value, index, 4).getFloat();
	}

	public static float bytesTofloat(byte[] value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getFloat();
		}
		else
		{
			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getFloat();
		}
	}

	public static double bytesTodouble(byte[] value)
	{
		return ByteBuffer.wrap(value).getDouble();
	}

	public static UUID bytesToUUID(byte[] src, int index)
	{
		byte[] arrValue = new byte[16];
		System.arraycopy(src, index, arrValue, 0, 16);
		return bytesToUUID(arrValue);

	}

	public static UUID bytesToUUID(byte[] src)
	{
		ByteBuffer buffer = ByteBuffer.wrap(src);
		buffer.order(ByteOrder.BIG_ENDIAN);
		long firstLong = buffer.getLong();
		long secondLong = buffer.getLong();

		return new UUID(firstLong, secondLong);
	}

	public static Date byte4ToDate(byte[] value)
	{
		int time = ConvertUtil.bytesToint(value);
		return ConvertUtil.intToDate(time);
	}

	public static Date byte4ToDate(byte[] value, int index)
	{
		int time = ConvertUtil.bytesToint(value, index);
		return ConvertUtil.intToDate(time);
	}

	public static Date byte8ToDate(byte[] value)
	{
		long time = ConvertUtil.bytesTolong(value);
		return ConvertUtil.longToDate(time);
	}

	public static Date byte8ToDate(byte[] value, int index)
	{
		long time = ConvertUtil.bytesTolong(value, index);
		return ConvertUtil.longToDate(time);
	}

	public static String bytesToString(byte[] src, int length)
	{
		byte[] arrValue = new byte[length];
		System.arraycopy(src, 0, arrValue, 0, length);
		return new String(arrValue);
	}

	public static String bytesToString(byte[] src, int length, int index)
	{
		byte[] arrValue = new byte[length];
		System.arraycopy(src, index, arrValue, 0, length);
		return new String(arrValue);
	}

	public static byte[] shortTobytes(short value)
	{
		return ByteBuffer.allocate(2).putShort(value).array();
	}

	public static byte[] shortTobytes(short value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort(value).array();
		}
		else
		{
			return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
		}
	}

	public static byte[] intTobytes(int value)
	{
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	public static byte[] intTobytes(int value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
		}
		else
		{
			return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
		}
	}

	public static byte[] longTobytes(long value)
	{
		return ByteBuffer.allocate(8).putLong(value).array();
	}


	public static byte[] floatTobytes(float value)
	{
		return ByteBuffer.allocate(4).putFloat(value).array();
	}

	public static byte[] floatTobytes(float value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(value).array();
		}
		else
		{
			return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
		}
	}

	public static byte[] doubleTobytes(double value)
	{
		return ByteBuffer.allocate(8).putDouble(value).array();
	}

	public static byte[] doubleTobytes(double value, boolean bigEndian)
	{
		if(bigEndian)
		{
			return ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putDouble(value).array();
		}
		else
		{
			return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(value).array();
		}
	}

	public static Date intToDate(int value)
	{
		long lValue = value*1000L;
		return new Date(lValue);
	}

	public static Date longToDate(long value)
	{
		return new Date(value);
	}

	public static byte[] UUIDToBytes(UUID uuid)
	{
		Long mostValue  = uuid.getMostSignificantBits();
		Long leastValue = uuid.getLeastSignificantBits();
		byte[] arrMostValue = ConvertUtil.longTobytes(mostValue);
		byte[] arrLeastValue = ConvertUtil.longTobytes(leastValue);
		byte[] arrUUID = new byte[16];
		System.arraycopy(arrMostValue, 0, arrUUID, 0, 8);
		System.arraycopy(arrLeastValue, 0, arrUUID, 8, 8);

		return arrUUID;
	}

	public static <T> T toObjectClone(Object value, Class<T> type) throws Exception {

		Gson gson = new Gson();

		String result = gson.toJson(value);
		return gson.fromJson(result,type);

	}

	public static byte toByteValue(byte value, int statIndex, int length)
	{
		if(statIndex < 0)
		{
			statIndex = 0;
		}

		int endIndex = statIndex + length;
		if(endIndex > 8)
		{
			endIndex = 8;
		}

		BitSet bitSet = new BitSet(8);
		bitSet.set(statIndex, endIndex);
		byte[] checkBytes = bitSet.toByteArray();
		if(checkBytes == null || checkBytes.length == 0)
		{
			return 0;
		}
		byte checkByte = checkBytes[0];
		byte bitValue  = (byte)(value & checkByte);
		bitValue = (byte) ((bitValue>>statIndex));

		return bitValue;
	}

//	public static byte[] doubleTobytes(double value)
//	{
//		return ByteBuffer.allocate(8).putDouble(value).array();
//	}
//
//	public static byte[] doubleTobytes(double value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putDouble(value).array();
//		}
//		else
//		{
//			return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(value).array();
//		}
//	}
//
//	public static byte[] floatTobytes(float value)
//	{
//		return ByteBuffer.allocate(4).putFloat(value).array();
//	}
//
//	public static byte[] floatTobytes(float value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(value).array();
//		}
//		else
//		{
//			return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array();
//		}
//	}
//
//	public static float bytesTofloat(byte[] value)
//	{
//		return ByteBuffer.wrap(value).getFloat();
//	}
//
//	public static double bytesTodouble(byte[] value)
//	{
//		return ByteBuffer.wrap(value).getDouble();
//	}
//
//	public static float bytesTofloat(byte[] value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getFloat();
//		}
//		else
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getFloat();
//		}
//	}
//
//	public static byte[] longTobytes(long value)
//	{
//		return ByteBuffer.allocate(8).putLong(value).array();
//	}
//
//	public static byte[] intTobytes(int value)
//	{
//		return ByteBuffer.allocate(4).putInt(value).array();
//	}
//
//	public static byte[] intTobytes(int value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
//		}
//		else
//		{
//			return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
//		}
//	}
//
//	public static long bytesTolong(byte[] value)
//	{
//		return ByteBuffer.wrap(value).getLong();
//	}
//
//	public static long bytesTolong(byte[] value, int index)
//	{
//		//TODO 업데이트
//		byte[] arrValue = new byte[8];
//		System.arraycopy(value, index, arrValue, 0, 8);
//		return bytesTolong(arrValue);
//	}
//
//	public static int bytesToint(byte[] value)
//	{
//		return ByteBuffer.wrap(value).getInt();
//	}
//
//	public static int bytesToint(byte[] value, int index)
//	{
//		//TODO 업데이트
//		byte[] arrValue = new byte[4];
//		System.arraycopy(value, index, arrValue, 0, 4);
//		return bytesToint(arrValue);
//	}
//
//	public static int bytesToint(byte[] value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getInt();
//		}
//		else
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getInt();
//		}
//	}
//
//	public static byte[] shortTobytes(short value)
//	{
//		return ByteBuffer.allocate(2).putShort(value).array();
//	}
//
//	public static byte[] shortTobytes(short value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort(value).array();
//		}
//		else
//		{
//			return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
//		}
//	}
//
//	public static short bytesToshort(byte[] value)
//	{
//		return ByteBuffer.wrap(value).getShort();
//	}
//
//	public static short bytesToshort(byte[] value, int index)
//	{
//		//TODO 업데이트
//		byte[] arrValue = new byte[2];
//		System.arraycopy(value, index, arrValue, 0, 2);
//		return bytesToshort(arrValue);
//	}
//
//	public static short bytesToshort(byte[] value, boolean bigEndian)
//	{
//		if(bigEndian)
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.BIG_ENDIAN).getShort();
//		}
//		else
//		{
//			return ByteBuffer.wrap(value).order(ByteOrder.LITTLE_ENDIAN).getShort();
//		}
//	}
//
//	public static UUID bytesToUUID(byte[] src, int index)
//	{
//		byte[] arrValue = new byte[16];
//		System.arraycopy(src, index, arrValue, 0, 16);
//		return bytesToUUID(arrValue);
//
//	}
//
//	public static UUID bytesToUUID(byte[] src)
//	{
//		ByteBuffer buffer = ByteBuffer.wrap(src);
//		buffer.order(ByteOrder.BIG_ENDIAN);
//		long firstLong = buffer.getLong();
//		long secondLong = buffer.getLong();
//
//		return new UUID(firstLong, secondLong);
//	}
//
//	public static byte[] UUIDToBytes(UUID uuid)
//	{
//		Long mostValue  = uuid.getMostSignificantBits();
//		Long leastValue = uuid.getLeastSignificantBits();
//		byte[] arrMostValue = ConvertUtil.longTobytes(mostValue);
//		byte[] arrLeastValue = ConvertUtil.longTobytes(leastValue);
//		byte[] arrUUID = new byte[16];
//		System.arraycopy(arrMostValue, 0, arrUUID, 0, 8);
//		System.arraycopy(arrLeastValue, 0, arrUUID, 8, 8);
//
//		return arrUUID;
//	}
//
//	public static <T> T toObjectClone(Object value, Class<T> type) throws Exception {
//
//		Gson gson = new Gson();
//
//		String result = gson.toJson(value);
//		return gson.fromJson(result,type);
//
//	}
//
//	public static byte toByteValue(byte value, int statIndex, int length)
//	{
//		if(statIndex < 0)
//		{
//			statIndex = 0;
//		}
//
//		int endIndex = statIndex + length;
//		if(endIndex > 8)
//		{
//			endIndex = 8;
//		}
//
//		BitSet bitSet = new BitSet(8);
//		bitSet.set(statIndex, endIndex);
//		byte[] checkBytes = bitSet.toByteArray();
//		if(checkBytes == null || checkBytes.length == 0)
//		{
//			return 0;
//		}
//		byte checkByte = checkBytes[0];
//		byte bitValue  = (byte)(value & checkByte);
//		bitValue = (byte) ((bitValue>>statIndex));
//
//		return bitValue;
//
//	}
}
