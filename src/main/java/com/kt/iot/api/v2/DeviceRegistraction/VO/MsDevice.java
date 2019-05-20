package com.kt.iot.api.v2.DeviceRegistraction.VO;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "spot_dev_bas")
@IdClass(DeviceId.class)
public class MsDevice implements Serializable {

	@Id
	@Column(name = "svc_dev_seq", nullable = false)
	private Long sequence;

	@Id
	@Column(name = "svc_tgt_seq", nullable = false)
	private Long targetSequnece;

	@Column(name = "dev_model_seq")
	private Long modelSequence;

	// 물리적 장치
	@Column(name = "spot_dev_id", nullable = false)
	private String spotDevId;

	@Column(name = "gw_cnct_id", nullable = false)
	private String connectionId;

	@Column(name = "del_yn", nullable = false)
	private String delYn;

	@Column(name = "cretr_id")
	private Long creator;
	@ManyToOne
	@JoinColumn(name = "dev_model_seq", referencedColumnName = "dev_model_seq", insertable = false, updatable = false)
	private DeviceModel model;
//	
	@ManyToOne
	@JoinColumn(name = "svc_tgt_seq", referencedColumnName = "svc_tgt_seq", insertable = false, updatable = false)
	private Target target;

	// JPA에서 id로 인식하기 때문에 발생하는 문제로 인해 Transient처리
	@Transient
	private String id;

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DeviceModel getModel() {
		return model;
	}

	public void setModel(DeviceModel model) {
		this.model = model;
	}

	public Long getModelSequence() {
		return modelSequence;
	}

	public void setModelSequence(Long modelSequence) {
		this.modelSequence = modelSequence;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public Long getTargetSequnece() {
		return targetSequnece;
	}

	public void setTargetSequnece(Long targetSequnece) {
		this.targetSequnece = targetSequnece;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public String getSpotDevId() {
		return spotDevId;
	}

	public void setSpotDevId(String spotDevId) {
		this.spotDevId = spotDevId;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
