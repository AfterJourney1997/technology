package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Device;
import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    Device selectByPrimaryKey(Integer id);

    List<Device> selectAll();

    int updateByPrimaryKey(Device record);

    Page<Device> selectDeviceListWithPropertyByPage();
}