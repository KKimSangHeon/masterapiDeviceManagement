package com.kt.iot.api.v2.DeviceRegistraction.VO;

import java.io.Serializable;

import lombok.Data;

@Data
public class DeviceId  implements Serializable {

	private Long sequence;
    private Long targetSequnece; 
    
    public DeviceId(Long sequence, Long targetSequnece) {
    	
    	this.sequence = sequence;
    	this.targetSequnece = targetSequnece;
    }
    
    public DeviceId() {
    	
    }
    

}
