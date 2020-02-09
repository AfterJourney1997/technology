package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.DevicePropertyMapper;
import com.technologygarden.dao.PropertyDeviceMapper;
import com.technologygarden.entity.DeviceProperty;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemPropertyDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("systemPropertyDeviceService")
public class SystemPropertyDeviceServiceImpl implements SystemPropertyDeviceService {

    private final PropertyDeviceMapper propertyDeviceMapper;
    private final DevicePropertyMapper devicePropertyMapper;

    @Autowired
    public SystemPropertyDeviceServiceImpl(PropertyDeviceMapper propertyDeviceMapper, DevicePropertyMapper devicePropertyMapper) {
        this.propertyDeviceMapper = propertyDeviceMapper;
        this.devicePropertyMapper = devicePropertyMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getFatherSystemPropertyDeviceListByPage(Integer pageNum, Integer pageSize, Integer kind) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectFatherSystemPropertyDeviceByKind(kind);
        PageInfo<?> pageInfo = new PageInfo<>(propertyDeviceList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice) {
        propertyDeviceMapper.insertSystemPropertyDeviceDynamic(propertyDevice);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteSystemPropertyDeviceById(Integer id) {

        // 判断删除项是否有被使用
        List<DeviceProperty> devicePropertyList = devicePropertyMapper.selectDevicePropertyByPropertyIdCategoryId(id);
        if(devicePropertyList.size() > 0){

            log.warn("删除设备属性中删除的属性仍有设备在使用 ---> propertyDeviceId：[{}]", id);
            return new ResultBean<>(ResultStatus.DELETE_ERROR);
        }

        propertyDeviceMapper.deleteByPrimaryKey(id);
        // 如果删除的是设备，其id会是其属性的category_id，根据这个即可删除对应属性
        propertyDeviceMapper.deleteSystemPropertyDeviceByCategoryId(id);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateSystemPropertyDeviceById(PropertyDevice propertyDevice) {

        propertyDeviceMapper.updateSystemPropertyDeviceByIdDynamic(propertyDevice);

        // 如修改的为设备，设备属性中的设备名也需修改
        if(propertyDevice.getCategoryName() != null){
            propertyDeviceMapper.updateSystemPropertyDeviceByCategoryId(propertyDevice);
        }
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, Integer categoryId, String categoryName, String propertyName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PropertyDevice> propertyDeviceList = propertyDeviceMapper.searchSystemPropertyDeviceByPage(categoryId, categoryName, propertyName);
        PageInfo<?> pageInfo = new PageInfo<>(propertyDeviceList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<PropertyDevice>> getFurniturePropertyDevice() {

        List<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectCateGoryByKind(2);
        return new ResultBean<>(propertyDeviceList);
    }

    @Override
    public ResultBean<List<PropertyDevice>> getPropertyByCategoryId(Integer categoryId) {

        List<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectPropertyByCategoryId(categoryId);
        return new ResultBean<>(propertyDeviceList);
    }

    @Override
    public ResultBean<List<PropertyDevice>> getDevicePropertyDevice() {

        List<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectCateGoryByKind(1);
        return new ResultBean<>(propertyDeviceList);
    }

    @Override
    public ResultBean<List<PropertyDevice>> getDeviceCategoryById(Integer propertyDeviceId) {

        List<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectDeviceCategoryById(propertyDeviceId);
        return new ResultBean<>(propertyDeviceList);
    }

    @Override
    public ResultBean<PageInfo<?>> searchFatherSystemPropertyDeviceByPage(Integer pageNum, Integer pageSize, String categoryName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PropertyDevice> propertyDeviceList = propertyDeviceMapper.searchFatherSystemPropertyDeviceByPage(categoryName);
        PageInfo<?> pageInfo = new PageInfo<>(propertyDeviceList);
        return new ResultBean<>(pageInfo);
    }
}
