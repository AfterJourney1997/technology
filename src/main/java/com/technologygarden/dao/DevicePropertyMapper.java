package com.technologygarden.dao;

import com.technologygarden.entity.PropertyDevice;

import java.util.List;

public interface DevicePropertyMapper {

    int insertForeach(List<PropertyDevice> propertyDeviceList);

    int deleteByDeviceByDeviceId(Integer deviceId);

    int updateByDeviceIdDynamic(List<PropertyDevice> propertyDeviceList);
}
