package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.PropertyDeviceMapper;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemPropertyDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemPropertyDeviceService")
public class SystemPropertyDeviceServiceImpl implements SystemPropertyDeviceService {

    private final PropertyDeviceMapper propertyDeviceMapper;

    @Autowired
    public SystemPropertyDeviceServiceImpl(PropertyDeviceMapper propertyDeviceMapper) {
        this.propertyDeviceMapper = propertyDeviceMapper;
    }

    @Override
    public ResultBean<Page<PropertyDevice>> getSystemPropertyDeviceListByPage(Integer pageNum, Integer pageSize, Integer categoryId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectSystemPropertyDeviceListByPage(categoryId);
        return new ResultBean<>(propertyDeviceList);
    }

    @Override
    public ResultBean insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice) {
        propertyDeviceMapper.insertSystemPropertyDeviceDynamic(propertyDevice);
        return new ResultBean();
    }

    @Override
    public ResultBean deleteSystemPropertyDeviceById(Integer id) {

        // 判断删除项是否有被使用
        if(1 != 1){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }

        propertyDeviceMapper.deleteByPrimaryKey(id);
        propertyDeviceMapper.deleteSystemPropertyDeviceByCategoryId(id);
        return new ResultBean();
    }

    @Override
    public ResultBean updateSystemPropertyDeviceById(PropertyDevice propertyDevice) {

        propertyDeviceMapper.updateSystemPropertyDeviceByIdDynamic(propertyDevice);

        // 如修改的为设备，设备属性中的设备名也需修改
        if(propertyDevice.getName() != null){
            propertyDeviceMapper.updateSystemPropertyDeviceByCategoryId(propertyDevice);
        }
        return new ResultBean();
    }

    @Override
    public ResultBean<Page<PropertyDevice>> searchSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, Integer categoryId, String device, String property) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PropertyDevice> propertyDeviceList = propertyDeviceMapper.searchSystemPropertyDeviceByPage(categoryId, device, property);
        return new ResultBean<>(propertyDeviceList);
    }
}
