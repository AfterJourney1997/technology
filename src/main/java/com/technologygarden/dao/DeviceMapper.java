package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertReturnPrimaryKey(Device device);

    Device selectByPrimaryKey(Integer id);

    List<Device> selectAll();

    int updateByPrimaryKey(Device record);

    List<Device> selectDeviceListWithPropertyList();

    int updateByIdDynamic(Device device);

    List<Device> searchDeviceListWithPropertyList(@Param("categoryId") Integer categoryId, @Param("deviceName") String deviceName, @Param("owner") Integer owner);




    List<Device> selectFurnitureListWithPropertyList();

    List<Device> searchFurnitureListWithPropertyList(@Param("categoryId") Integer categoryId, @Param("furnitureName") String furnitureName, @Param("owner") Integer owner);


}