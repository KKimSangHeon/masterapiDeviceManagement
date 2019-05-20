package com.kt.iot.api.message.vo;

import java.util.HashMap;

public class MqCode
{
	public static enum PltfrmMsgVer
	{
		/** 버전 2.0 */
		VER_02_00( (byte)0x20 ),
		/** 버전 3.0 */
		VER_03_00( (byte)0x30 ),
		;
		private final Byte value;

        private PltfrmMsgVer(Byte value) {
                this.value = value;
        }

        public boolean equals(Byte obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public Byte getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<Byte, PltfrmMsgVer> map = new HashMap<Byte, PltfrmMsgVer>();
		static {
			for(PltfrmMsgVer it : values()) {
				map.put(it.getValue(), it);
			}
		}
		// value에 해당되는 enum을 반환
		public static PltfrmMsgVer fromByte(Byte value) {
			return map.get(value);
		}
	}

	/**
	 * 헤더타입
	 * @since	: 2017. 9. 25.
	 * @author	: 추병조
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2017. 9. 25. 추병조: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum HeaderType
	{
		/** 기본 */
		BASIC( (byte)1 ),
		/** 경량헤더 */
		LIGHT_WEIGHT( (byte)2 ),
		;
		private final Byte value;

        private HeaderType(Byte value) {
                this.value = value;
        }

        public boolean equals(Byte obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public Byte getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<Byte, HeaderType> map = new HashMap<Byte, HeaderType>();
		static {
			for(HeaderType it : values()) {
				map.put(it.getValue(), it);
			}
		}
		// value에 해당되는 enum을 반환
		public static HeaderType fromByte(Byte value) {
			return map.get(value);
		}
	}


	/**
	 * 암호화유형
	 * @since	: 2017. 9. 25.
	 * @author	: 추병조
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2017. 9. 25. 추병조: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum EncryptionType
	{
		/** Plain Text */
		PLAIN_TEXT( (byte)0x0 ),
		/** AES 128 */
		AES_128( (byte)0x1 ),
		/** AES 256 */
		AES_256( (byte)0x2 ),
		/** DES 128 */
		DES_64( (byte)0x11 ),
		/** DES 256 */
		DES_EDE_192( (byte)0x12 ),
		/** SEED 128 */
		SEED_128( (byte)0x21 ),
		/** ARIA 128 */
		ARIA_128( (byte)0x31 ),
		/** ARIA 256 */
		ARIA_192( (byte)0x32 ),
		/** ARIA 256 */
		ARIA_256( (byte)0x33 ),
		;

		private final Byte value;

        private EncryptionType(Byte value) {
                this.value = value;
        }

        public boolean equals(Byte obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public Byte getValue() {
			return value;
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value.toString();
		}


		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<Byte, EncryptionType> map = new HashMap<Byte, EncryptionType>();
		static {
			for(EncryptionType it : values()) {
				map.put(it.getValue(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static EncryptionType fromByte(Byte value) {
			return map.get(value);
		}

	}

	/**
	 * 인코딩유형
	 * @since	: 2017. 9. 25.
	 * @author	: 추병조
	 * <PRE>
	 * Revision History
	 * ----------------------------------------------------
	 * 2017. 9. 25. 추병조: 최초작성
	 * ----------------------------------------------------
	 * </PRE>
	 */
	public static enum EncodingType
	{
		/** userDefined */
		USER_DEFINED( (byte)0x1 ),
		/** xml */
		XML( (byte)0x2 ),
		/** json */
		JSON( (byte)0x3 ),
		/** gpb */
		GPB( (byte)0x10 ),
		/** thrift */
		THRIFT( (byte)0x11 ),
		/** avro */
		AVRO( (byte)0x12 ),
		/** pcre */
		PCRE( (byte)0x13 ),
		;

		private final Byte value;

        private EncodingType(Byte value) {
                this.value = value;
        }

        public boolean equals(Byte obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public Byte getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<Byte, EncodingType> map = new HashMap<Byte, EncodingType>();
		static {
			for(EncodingType it : values()) {
				map.put(it.getValue(), it);
			}
		}
		// value에 해당되는 enum을 반환
		public static EncodingType fromByte(Byte value) {
			return map.get(value);
		}
	}

	public static enum MessageType
	{
		/** KT표준아님 */
		NON_KT_STANDART( ((short)0) ),
//		/** 플랫폼 및 어댑터 버전 조회 */
//		VER_GWVER_RQT( ((short)1111) ),
//		/** 외부시스템 TCP 로그인  */
//		ATHN_LOGINATHN_EXTRSYS_TCP( ((short)2121) ),
//		/** 장치 TCP 로그인 */
//		ATHN_LOGINATHN_DEV_TCP( ((short)2141) ),
//		/** 외부시스템 TCP 채널 인증 */
//		ATHN_COMMCHATHN_EXTRSYS_TCP( ((short)2221) ),
		/** 장치 TCP 채널 인증 */
		ATHN_COMMCHATHN_DEV_TCP( ((short)2241) ),
//		/** KeepAlive TCP 채널 */
//		KEEP_ALIVE_COMMCHATHN_TCP( ((short)2311) ),
//		/** 데이터유형 조회 */
//		INITA_DATATYPE_RETV( ((short)3111) ),
//		/** 외부시스템정보 조회 */
//		INITA_EXTRSYS_RETV( ((short)3211) ),
//		/** 외부시스템정보 갱신보고 */
//		INITA_EXTRSYS_UDATERPRT( ((short)3221) ),
		/** 장치정보 조회(내부) */
		INITA_DEV_RETV( ((short)3311) ),
		/** 장치정보 조회 응답(내부) */
		INITA_DEV_RETV_RESLT( ((short)3312) ),
		/** 장치정보 갱신보고(외부) */
		INITA_DEV_UDATERPRT_EXTR( ((short)3321) ),
		/** 장치정보 조회(외부) */
		INITA_DEV_RETV_EXTR( ((short)3331) ),
		/** 장치정보 조회 응답(외부) */
		INITA_DEV_RETV_RESLT_EXTR( ((short)3332) ),
		/** 장치정보 갱신보고(내부)*/
		INITA_DEV_UDATERPRT( ((short)3341) ),
		/** 장치정보 갱신보고(내부) 응답*/
		INITA_DEV_UDATERPRT_RESLT( ((short)3342) ),
		/** 수집설정 필터링조건조회 */
		COLEC_SETUP_FLTRCONDRETV( ((short)3411) ),
		/** 수집설정 필터링조건조회 응답*/
		COLEC_SETUP_FLTRCONDRETV_RESLT( ((short)3412) ),
		/** 수집설정 필터링조건설정 */
		COLEC_SETUP_FLTRCONDSETUP( ((short)3421) ),
		/** 수집설정 필터링조건설정 응답*/
		COLEC_SETUP_FLTRCONDSETUP_RESLT( ((short)3422) ),
		/** 수집설정 필터링조건해지 */
		COLEC_SETUP_FLTRCONDTRMN( ((short)3431) ),
		/** 수집설정 필터링조건해지 응답*/
		COLEC_SETUP_FLTRCONDTRMN_RESLT( ((short)3432) ),
//		/** 수집설정 이벤트조건조회 */
//		COLEC_SETUP_EVCONDRETV( ((short)3441) ),
//		/** 수집설정 이벤트조건해지 */
//		COLEC_SETUP_EVCONDTRMN( ((short)3461) ),
		/** 수집 통합데이터 */
		COLEC_ITGDATA_RECV( ((short)4111) ),
		/** 수집 통합데이터 디바이스 다수행  */
		COLEC_ITGDATA_DEV_MULTI_ROW_RECV( ((short)4121) ),
		/** 수집 통합데이터 디바이스 단건행*/
		COLEC_ITGDATA_DEV_ROW_RECV( ((short)4131) ),
//		/** 수집 센싱데이터 */
//		COLEC_SNSNDATA_RECV( ((short)4211) ),
//		/** 수집 위치데이터 */
//		COLEC_LODATA_RECV( ((short)4311) ),
//		/** 수집 상태데이터 */
//		COLEC_STTUSDATA_RECV( ((short)4411) ),
//		/** 수집 이진데이터 */
//		COLEC_BINDATA_RECV( ((short)4511) ),
//		/** 수집 이벤트 */
//		COLEC_EVDATA_RECV( ((short)4611) ),
//		/** 시스템제어 실시간제어 */
//		CONTL_SYS_RTIMECONTL( ((short)5111) ),
//		/** 시스템제어 설정변경 */
//		CONTL_SYS_SETUPCHG( ((short)5121) ),
//		/** 장치제어 실시간제어 */
//		CONTL_DEV_RTIMECONTL( ((short)5211) ),
//		/** 장치제어 설정변경 */
//		CONTL_DEV_SETUPCHG( ((short)5221) ),
//		/** 장치제어 펌웨어업데이트 */
//		CONTL_DEV_FRMWRUDATE( ((short)5231) ),
		/** 통합데이터 전달 */
		CONTL_ITGCNVY_DATA( ((short)5251) ),
		/** 통합데이터 전달 응답*/
		CONTL_ITGCNVY_DATA_RESLT( ((short)5252) ),
//		/** 이진데이터 전달 */
//		CNVY_BIN_DATA( ((short)5261) ),
//		/** 시스템점검 체크패킷수신 */
//		CHK_SYS_CHKPACKTRCV( ((short)6121) ),
//		/** 장치점검 체크패킷수신 */
//		CHK_DEV_CHKPACKTRCV( ((short)6221) ),
		/** 최종값쿼리 */
		QRY_LAST_VAL( ((short)7121) ),
		/** 최종값쿼리 응답*/
		QRY_LAST_VAL_RESLT( ((short)7122) ),
		/** 펌웨어정보조회 */
		FRMWR_INFO_RETV( ((short)8111) ),
		/** 펌웨어정보조회 응답*/
		FRMWR_INFO_RETV_RESLT( ((short)8112) ),
		/** 펌웨어업데이트 통지 */
		FRMWR_UDATE_NTFY( ((short)8121) ),
		/** 펌웨어업데이트 통지 응답*/
		FRMWR_UDATE_NTFY_RESLT( ((short)8122) ),
		/** 펌웨어 업데이트상태  */
		FRMWR_UDATE_STTUS( ((short)8131) ),
		/** 펌웨어 업데이트상태 응답*/
		FRMWR_UDATE_STTUS_RESLT( ((short)8132) ),
		/** 펌웨어데이터 조회 HTTP */
		FRMWR_DATA_RETV_HTTP( ((short)8141) ),
		/** 펌웨어데이터 조회 HTTP 응답 */
		FRMWR_DATA_RETV_HTTP_RESLT( ((short)8142) ),
		/** 패키지데이터 HTTP 요청 */
		PKG_DATA_RETV_HTTP( ((short)8151) ),
		/** 패키지데이터 HTTP 요청 응답 */
		PKG_DATA_RETV_HTTP_RESLT( ((short)8152) ),
		/** 펌웨어데이터 TCP 요청 */
		FRMWR_DATA_RETV_TCP( ((short)8161) ),
		/** 펌웨어데이터 TCP 요청 응답 */
		FRMWR_DATA_RETV_TCP_RESLT( ((short)8162) ),
		/** 패키지데이터 TCP 요청 */
		PKG_DATA_RETV_TCP( ((short)8171) ),
		/** 패키지데이터 TCP 요청 응답 */
		PKG_DATA_RETV_TCP_RESLT( ((short)8172) ),
		/** 통합로그수집 */
		LOG_ITG_LOG( ((short)8211) ),
		/** 통합로그수집 디바이스 다수행*/
		LOG_ITG_LOG_DEV_MULTI_ROW( ((short)8221) ),
		/** 통합로그수집 디바이스 단건행 */
		LOG_ITG_LOG_DEV_ROW( ((short)8231) ),
		;

		private final Short value;

        private MessageType(Short value) {
                this.value = value;
        }

        public boolean equals(Short obj)
    	{
        	return value.equals(obj);
    	}

		/**
		 * @return the value
		 */
		public Short getValue() {
			return value;
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value.toString();
		}


		// value에 해당되는 enum을 반환하기 위한 Map 생성 및 설정
		private static final HashMap<Short, MessageType> map = new HashMap<Short, MessageType>();
		static {
			for(MessageType it : values()) {
				map.put(it.getValue(), it);
			}
		}

		// value에 해당되는 enum을 반환
		public static MessageType fromShort(Short value) {
			return map.get(value);
		}
	}
}
