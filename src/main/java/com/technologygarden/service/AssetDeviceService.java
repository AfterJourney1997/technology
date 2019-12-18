package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AssetDeviceService {

    ResultBean<PageInfo<?>> getDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertDeviceWithPropertyDynamic(Device device);

    ResultBean<?> deleteDeviceById(Integer deviceId);

    ResultBean<?> updateDeviceById(Device device);

    ResultBean<PageInfo<?>> searchDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String deviceName, Integer owner);

    ResultBean<?> distributeDevice(Integer deviceId, Integer deviceNum, Integer companyId, Integer roomId);





    ResultBean<PageInfo<?>> getFurnitureListWithPropertyByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertFurnitureWithPropertyDynamic(Device furniture);

    ResultBean<PageInfo<?>> searchFurnitureListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String furnitureName, Integer owner);


}
