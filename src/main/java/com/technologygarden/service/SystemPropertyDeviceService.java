package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;

public interface SystemPropertyDeviceService {

    ResultBean<PageInfo<?>> getFatherSystemPropertyDeviceListByPage(Integer pageNum, Integer pageSize, Integer kind);

    ResultBean<?> insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice);

    ResultBean<?> deleteSystemPropertyDeviceById(Integer id);

    ResultBean<?> updateSystemPropertyDeviceById(PropertyDevice propertyDevice);

    ResultBean<PageInfo<?>> searchSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, Integer categoryId, String categoryName, String propertyName);

    ResultBean<List<PropertyDevice>> getFurniturePropertyDevice();

    ResultBean<List<PropertyDevice>> getPropertyByCategoryId(Integer categoryId);

    ResultBean<List<PropertyDevice>> getDevicePropertyDevice();

    ResultBean<List<PropertyDevice>> getDeviceCategoryById(Integer propertyDeviceId);

    ResultBean<PageInfo<?>> searchFatherSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, String categoryName);
}
