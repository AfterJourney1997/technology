package com.technologygarden.dao;

import com.technologygarden.entity.DeviceProperty;
import com.technologygarden.entity.PropertyDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevicePropertyMapper {

    int insertForeach(List<PropertyDevice> propertyDeviceList);

    int deleteByDeviceByDeviceId(Integer deviceId);

    int updateByDeviceIdDynamic(List<PropertyDevice> propertyDeviceList);

    List<DeviceProperty> selectDevicePropertyByPropertyIdCategoryId(@Param("propertyId") Integer propertyId);
}
