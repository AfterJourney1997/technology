package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface SystemPropertyDeviceService {

    ResultBean<Page<PropertyDevice>> getSystemPropertyDeviceListByPage(Integer pageNum, Integer pageSize, Integer categoryId);

    ResultBean insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice);

    ResultBean deleteSystemPropertyDeviceById(Integer id);

    ResultBean updateSystemPropertyDeviceById(PropertyDevice propertyDevice);

    ResultBean<Page<PropertyDevice>> searchSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, Integer categoryId, String device, String property);
}
