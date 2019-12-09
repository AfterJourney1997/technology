package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;

public interface SystemPropertyDeviceService {

    ResultBean<Page<PropertyDevice>> getSystemPropertyDeviceListByPage(Integer pageNum, Integer pageSize, Integer categoryId);

    ResultBean<?> insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice);

    ResultBean<?> deleteSystemPropertyDeviceById(Integer id);

    ResultBean<?> updateSystemPropertyDeviceById(PropertyDevice propertyDevice);

    ResultBean<Page<PropertyDevice>> searchSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, Integer categoryId, String categoryName, String propertyName);

    ResultBean<List<PropertyDevice>> getFurniturePropertyDevice();

    ResultBean<List<PropertyDevice>> getPropertyByCategoryId(Integer categoryId);

    ResultBean<List<PropertyDevice>> getDevicePropertyDevice();
}
