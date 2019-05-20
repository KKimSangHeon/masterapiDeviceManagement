package com.kt.iot.api.v2.DeviceRegistraction.VO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "svc_tgt_bas")
public class Target {
	
	public Target() {
	}
	
	
	public Target(Long sequence) {
		this.sequence = sequence;
	}

	@Id
	@Column(name = "svc_tgt_seq", nullable = false)
	private Long sequence;
	
	@Column(name = "svc_dist_cd", nullable = false)
	private String districtCode;
	
	@Column(name = "svc_thme_cd", nullable = false)
	private String themeCode;
	
	@Column(name = "svc_cd", nullable = false)
	private String serviceCode;

	@Column(name = "svc_creator", nullable = false)
	private String creator;

	@Column(name = "id", nullable = false)
	private String id;	
	
	@Transient
	private String delYn;

	public Long getSequence() {
		return sequence;
	}


	public void setSequence(Long sequence) {
		this.sequence = sequence;
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


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDelYn() {
		return delYn;
	}


	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	
}
