package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AssetDeviceService {

    ResultBean<Page<Device>> getDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertDeviceWithPropertyDynamic(Device device);

    ResultBean<?> deleteDeviceById(Integer deviceId);

    ResultBean<?> updateDeviceById(Device device);

    ResultBean<Page<Device>> searchDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String deviceName, Integer owner);
}
