package com.kt.iot.api.v2.DeviceRegistraction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.iot.api.v2.DeviceRegistraction.VO.DeviceId;
import com.kt.iot.api.v2.DeviceRegistraction.VO.MsDevice;


@Repository
public interface DeviceRepository extends JpaRepository<MsDevice, DeviceId>{
	
	public MsDevice findBySpotDevIdAndConnectionIdAndDelYn(String spotDevId, String connectionId, String delYn);
	
	public List<MsDevice> findByTargetSequneceOrderBySequenceAsc(Long sequence);
}
