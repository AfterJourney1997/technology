package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    Device selectByPrimaryKey(Integer id);

    List<Device> selectAll();

    int updateByPrimaryKey(Device record);

    Page<Device> selectDeviceListWithPropertyByPage();

    int updateByIdDynamic(Device device);

    Page<Device> searchDeviceListWithPropertyByPage(@Param("categoryId") Integer categoryId, @Param("deviceName") String deviceName, @Param("owner") Integer owner);




    Page<Device> selectFurnitureListWithPropertyByPage();

    Page<Device> searchFurnitureListWithPropertyByPage(@Param("categoryId") Integer categoryId, @Param("furnitureName") String furnitureName, @Param("owner") Integer owner);


}