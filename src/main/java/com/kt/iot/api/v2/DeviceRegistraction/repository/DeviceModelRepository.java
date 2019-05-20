package com.kt.iot.api.v2.DeviceRegistraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.iot.api.v2.DeviceRegistraction.VO.DeviceId;
import com.kt.iot.api.v2.DeviceRegistraction.VO.DeviceModel;
import com.kt.iot.api.v2.DeviceRegistraction.VO.MsDevice;


@Repository

public interface DeviceModelRepository extends JpaRepository<DeviceModel, Long>{

}
