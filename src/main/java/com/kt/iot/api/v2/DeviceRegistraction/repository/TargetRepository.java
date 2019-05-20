package com.kt.iot.api.v2.DeviceRegistraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.iot.api.v2.DeviceRegistraction.VO.DeviceId;
import com.kt.iot.api.v2.DeviceRegistraction.VO.MsDevice;
import com.kt.iot.api.v2.DeviceRegistraction.VO.Target;


@Repository
public interface TargetRepository extends JpaRepository<Target, Long>{

}
