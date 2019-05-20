package com.kt.iot.api.VO.Contract;

import com.kt.iot.api.VO.Common.Id;
import com.kt.iot.api.VO.Creator.Creator;

public class Contract {

	String id;
	Long Sequence;
	String contract;
	Creator creator;
	
	public Contract(Long sequence) {
		super();
		Sequence = sequence;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the sequence
	 */
	public Long getSequence() {
		return Sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Long sequence) {
		Sequence = sequence;
	}
	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}
	/**
	 * @return the creator
	 */
	public Creator getCreator() {
		return creator;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contract [id=" + id + ", Sequence=" + Sequence + ", contract=" + contract + ", creator=" + creator
				+ "]";
	}

}
