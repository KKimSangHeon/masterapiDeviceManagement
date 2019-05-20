package com.kt.iot.api.VO.Model;

import java.util.List;

import com.kt.iot.api.VO.Common.Id;
import com.kt.iot.api.VO.Common.Sequence;

public class Model {
	
	String id;
	Sequence sequence;
	List<Function> functions;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the functions
	 */
	public List<Function> getFunctions() {
		return functions;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Model [id=" + id + ", functions=" + functions + "]";
	}

}
