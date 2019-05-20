package com.kt.iot.api.VO.Creator;

import com.kt.iot.api.VO.Common.Sequence;

public class Creator {

	String id;
	Long sequence;
	
	public Creator() {
		// TODO Auto-generated constructor stub
	}
	public Creator(String id){
		this.id = id;
	}
	public Creator(Long sequence){
		this.sequence = sequence;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sequence
	 */
	public Long getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Creator [id=" + id + ", sequence=" + sequence + "]";
	}
	public boolean equals(Creator creator){
		if(this.id != null && creator != null && creator.getId() != null){
			
			if(this.id.equals(creator.getId())) 
				return true;
			else return false;
		}
		return false;
	}

}
