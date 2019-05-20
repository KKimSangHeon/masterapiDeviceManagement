package com.kt.iot.api.VO.Device;

import com.kt.iot.api.VO.Common.Id;
import com.kt.iot.api.VO.Common.Sequence;
import com.kt.iot.api.VO.Contract.Contract;
import com.kt.iot.api.VO.Creator.Creator;
import com.kt.iot.api.VO.Model.Model;

public class Device {

	String id;	
	Sequence sequence;
	Creator creator;
	Model model;
	Contract contract;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the sequence
	 */
	public Sequence getSequence() {
		return sequence;
	}
	/**
	 * @return the creator
	 */
	public Creator getCreator() {
		return creator;
	}
	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Device [id=" + id + ", sequence=" + sequence + ", creator=" + creator + ", model=" + model
				+ ", contract=" + contract + "]";
	}

	
//	Id id;	
//	Sequence sequence;
//	Creator creator;
//	Model model;
//	Contract contract;
//	/**
//	 * @return the id
//	 */
//	public Id getId() {
//		return id;
//	}
//	/**
//	 * @return the creator
//	 */
//	public Creator getCreator() {
//		return creator;
//	}
//	/**
//	 * @return the sequence
//	 */
//	public Sequence getSequence() {
//		return sequence;
//	}
//	/**
//	 * @param sequence the sequence to set
//	 */
//	public void setSequence(Sequence sequence) {
//		this.sequence = sequence;
//	}
//	/**
//	 * @return the model
//	 */
//	public Model getModel() {
//		return model;
//	}
//	/**
//	 * @return the contract
//	 */
//	public Contract getContract() {
//		return contract;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(Id id) {
//		this.id = id;
//	}
//	/**
//	 * @param creator the creator to set
//	 */
//	public void setCreator(Creator creator) {
//		this.creator = creator;
//	}
//	/**
//	 * @param model the model to set
//	 */
//	public void setModel(Model model) {
//		this.model = model;
//	}
//	/**
//	 * @param contract the contract to set
//	 */
//	public void setContract(Contract contract) {
//		this.contract = contract;
//	}
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "Device [id=" + id + ", sequence=" + sequence + ", creator=" + creator + ", model=" + model
//				+ ", contract=" + contract + "]";
//	}
	
	
	
}
