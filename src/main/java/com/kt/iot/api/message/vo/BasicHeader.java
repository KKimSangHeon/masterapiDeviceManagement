package com.kt.iot.api.message.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 메세지클래스
 * @since	: 2017. 9. 25.
 * @author	: 추병조
 * <PRE>
 * Revision History
 * ----------------------------------------------------
 * 2017. 9. 25. 추병조: 최초작성
 * ----------------------------------------------------
 * </PRE>
 */
public class BasicHeader implements Serializable, Cloneable
{
	/** 직렬화아이디 */
	private static final long serialVersionUID = 176395149791496586L;

	/** 페이로드 콘텐츠 타입 */
	protected String contentType;
	/** 출발지 모듈명 */
	protected String from;
	/** 출발지 인스턴스명 */
	protected String fromInstance;
	/** 목적지 모듈명 */
	protected List<String> to;
	/** 목적지 인스턴스명 */
	protected List<String> toInstance;
	/** 리소스 구분자(그룹태그) */
	protected String resourceUri;
	/** E2E 트랜잭션처리에 필요한 아이디 */
	protected String e2eTransactionId;
	/** 공통응답코드 */
	protected Short resultCode;
	/** 메세지별 상세코드 */
	protected Short messageResultCode;
	/** 확장필드 */
	protected Map<String, String> extensions;

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

	public BasicHeader()
	{

	}
	public BasicHeader(String from, String fromInstance, String e2eTransactionId)
	{
		this.from = from;
		this.fromInstance = fromInstance;
		this.e2eTransactionId = e2eTransactionId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromInstance() {
		return fromInstance;
	}

	public void setFromInstance(String fromInstance) {
		this.fromInstance = fromInstance;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getToInstance() {
		return toInstance;
	}

	public void setToInstance(List<String> toInstance) {
		this.toInstance = toInstance;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getE2eTransactionId() {
		return e2eTransactionId;
	}

	public void setE2eTransactionId(String e2eTransactionId) {
		this.e2eTransactionId = e2eTransactionId;
	}

	public Short getResultCode() {
		return resultCode;
	}

	public void setResultCode(Short resultCode) {
		this.resultCode = resultCode;
	}

	public Short getMessageResultCode() {
		return messageResultCode;
	}

	public void setMessageResultCode(Short messageResultCode) {
		this.messageResultCode = messageResultCode;
	}

	public Map<String, String> getExtensions() {
		return extensions;
	}

	public void setExtensions(Map<String, String> extensions) {
		this.extensions = extensions;
	}


}
