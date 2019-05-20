package com.kt.iot.api.v2.DeviceRegistraction.VO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dev_model_bas")
public class DeviceModel {
	@Id
	@Column(name = "dev_model_seq", nullable = false)
	private long sequence;
	
	@Column(name = "dev_model_cd")
	private String id;

	@Column(name = "dev_model_nm", nullable = false)
	private String name;

	@Column(name = "dev_type_cd", nullable = false)
	private String type;					// 모델 유형 (A007 코드 그룹)
	
	@Column(name = "terml_makr_nm")
	private String manufacturer;

	@Column(name = "prot_type")
	private String protocolType;

	@Column(name = "bind_type_cd")
	private String bindingType;

	@Column(name = "dev_model_parent", nullable = false)
	private String parent;

	@Column(name = "cretr_id")
	private Long creator;
	
	@Column(name = "cret_dt")
	private Date createdOn;

	@Column(name = "amdr_id")
	private String modifier;
	
	@Column(name = "amdr_dt")
	private Date modifiedOn;
	
	@Column(name = "district_cd")
	private String districtCode;		

	@Column(name = "them_cd")
	private String themeCode;		
	
	@Column(name = "svc_cd")
	private String serviceCode;
	
	@Transient
	private String subsYn;			//구독 여부

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getBindingType() {
		return bindingType;
	}

	public void setBindingType(String bindingType) {
		this.bindingType = bindingType;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getThemeCode() {
		return themeCode;
	}

	public void setThemeCode(String themeCode) {
		this.themeCode = themeCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getSubsYn() {
		return subsYn;
	}

	public void setSubsYn(String subsYn) {
		this.subsYn = subsYn;
	}


	
	
	
}
