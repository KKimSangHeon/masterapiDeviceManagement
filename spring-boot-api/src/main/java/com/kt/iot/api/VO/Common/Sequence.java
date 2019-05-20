package com.kt.iot.api.VO.Common;

public class Sequence {

	Long Sequence;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sequence [Sequence=" + Sequence + "]";
	}
}
