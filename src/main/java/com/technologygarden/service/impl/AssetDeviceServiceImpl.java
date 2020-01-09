package com.technologygarden.service.impl;

import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.dao.DevicePropertyMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.DeviceProperty;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.AssetDeviceService;
import com.technologygarden.util.PageUtil;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("assetDeviceService")
public class AssetDeviceServiceImpl implements AssetDeviceService {

    private final DeviceMapper deviceMapper;
    private final DevicePropertyMapper devicePropertyMapper;
    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;

    @Autowired
    public AssetDeviceServiceImpl(DeviceMapper deviceMapper, DevicePropertyMapper devicePropertyMapper, CompanyRoomDeviceMapper companyRoomDeviceMapper) {
        this.deviceMapper = deviceMapper;
        this.devicePropertyMapper = devicePropertyMapper;
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize) {

        List<Device> deviceList = deviceMapper.selectDeviceListWithPropertyList();
        PageInfo<Device> pageInfo = PageUtil.startPage(deviceList, pageNum, pageSize);
        return new ResultBean<>(pageInfo);

    }

    @Override
    @Transactional
    public ResultBean<?> insertDeviceWithPropertyDynamic(Device device) {

        // 判断参数是否缺失
        if(device.getCategoryId() == null || StringUtil.empty(device.getDeviceName()) || device.getOwner() == null || device.getPiece() == null){
            log.warn("设备插入中缺失必须参数categoryId/deviceName/owner/piece：[{}]", device);
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        List<PropertyDevice> propertyDeviceList = device.getPropertyDeviceList();

        for (PropertyDevice propertyDevice : propertyDeviceList) {

            DeviceProperty deviceProperty = propertyDevice.getDeviceProperty();
            if (deviceProperty.getCategoryId() == null  || deviceProperty.getPropertyId() == null || StringUtil.empty(deviceProperty.getPropertyValue())) {

                log.warn("设备插入中属性对象中缺失必须参数categoryId/propertyId/propertyValue：[{}]", deviceProperty);
                return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
            }

        }

        // 1为设备
        device.setKind(1);
        device.setRemain(device.getTotal());
        // 先插入设备信息
        deviceMapper.insertReturnPrimaryKey(device);
        // 再插入设备相关属性
        for (PropertyDevice propertyDevice : propertyDeviceList) {
            propertyDevice.getDeviceProperty().setDeviceId(device.getDeviceId());
        }
        devicePropertyMapper.insertForeach(propertyDeviceList);
        return new ResultBean<>();

    }

    @Override
    @Transactional
    public ResultBean<?> deleteDeviceById(Integer deviceId) {

        // 判断设备是否被使用
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if(!device.getTotal().equals(device.getRemain())){

            log.warn("删除的设备仍被使用 ---> deviceId：" + deviceId);
            return new ResultBean<>(ResultStatus.DELETE_ERROR);
        }

        devicePropertyMapper.deleteByDeviceByDeviceId(deviceId);
        deviceMapper.deleteByPrimaryKey(deviceId);
        return new ResultBean<>();
    }

    @Override
    @Transactional
    public ResultBean<?> updateDeviceById(Device device) {

        // 判断id是否缺失
        if (device.getDeviceId() == null){
            log.warn("修改设备中设备主键缺失 ---> [{}]", device);
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        List<PropertyDevice> propertyDeviceList = device.getPropertyDeviceList();
        if(propertyDeviceList.size() > 0){
            for (PropertyDevice propertyDevice : propertyDeviceList) {

                DeviceProperty deviceProperty = propertyDevice.getDeviceProperty();
                if (deviceProperty.getDevicePropertyId() == null) {
                    log.warn("修改设备中设备属性主键缺失 ---> [{}]", deviceProperty);
                    return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
                }

            }
        }

        deviceMapper.updateByIdDynamic(device);
        devicePropertyMapper.updateByDeviceIdDynamic(propertyDeviceList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String deviceName, Integer owner) {

        List<Device> deviceList = deviceMapper.searchDeviceListWithPropertyList(categoryId, deviceName, owner);
        PageInfo<Device> pageInfo = PageUtil.startPage(deviceList, pageNum, pageSize);
        return new ResultBean<>(pageInfo);

    }

    @Override
    @Transactional
    public ResultBean<?> distributeDevice(Integer deviceId, Integer deviceNum, Integer companyId, Integer roomId) {

        // 判断是否有足够的数量可供分配
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        if(device.getRemain() <= 0 || device.getRemain() < deviceNum){
            log.warn("设备分配中无足够数量可供分配 ---> deviceId：[{}]", deviceId);
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 判断是否已分配过，已分配需修改之前分配记录的数量
        CompanyRoomDevice companyRoomDevice = companyRoomDeviceMapper.selectByCompanyDeviceRoom(companyId, deviceId, roomId);
        if(companyRoomDevice != null){
            companyRoomDeviceMapper.updateNumber(companyRoomDevice.getCrdId(), companyRoomDevice.getCrdNumber()+deviceNum);
        }else {
            companyRoomDeviceMapper.insert(CompanyRoomDevice.builder()
                        .crdCompanyId(companyId)
                        .crdRoomId(roomId)
                        .crdDeviceId(deviceId)
                        .crdNumber(deviceNum)
                        .build());
        }

        // 修改设备剩余数量
        deviceMapper.updateByIdDynamic(Device.builder()
                        .deviceId(deviceId)
                        .remain(device.getRemain() - deviceNum)
                        .build());

        return new ResultBean<>();
    }













    @Override
    public ResultBean<PageInfo<?>> getFurnitureListWithPropertyByPage(Integer pageNum, Integer pageSize) {

        List<Device> deviceList = deviceMapper.selectFurnitureListWithPropertyList();
        PageInfo<Device> pageInfo = PageUtil.startPage(deviceList, pageNum, pageSize);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<?> insertFurnitureWithPropertyDynamic(Device furniture) {

        // 判断参数是否缺失
        if(furniture.getCategoryId() == null || StringUtil.empty(furniture.getDeviceName()) || furniture.getOwner() == null || furniture.getPiece() == null){
            log.warn("家具插入中缺失必须参数categoryId/deviceName/owner/piece：" + furniture);
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        List<PropertyDevice> propertyDeviceList = furniture.getPropertyDeviceList();

        for (PropertyDevice propertyDevice : propertyDeviceList) {

            DeviceProperty deviceProperty = propertyDevice.getDeviceProperty();
            if (deviceProperty.getCategoryId() == null || deviceProperty.getPropertyId() == null || StringUtil.empty(deviceProperty.getPropertyValue())) {
                log.warn("家具插入中属性对象中缺失必须参数categoryId/propertyId/propertyValue：" + deviceProperty);
                return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
            }

        }

        // 2为家具
        furniture.setKind(2);
        furniture.setRemain(furniture.getTotal());
        // 先插入设备信息
        deviceMapper.insertReturnPrimaryKey(furniture);
        // 再插入设备相关属性
        for (PropertyDevice propertyDevice : propertyDeviceList) {
            propertyDevice.getDeviceProperty().setDeviceId(furniture.getDeviceId());
        }
        devicePropertyMapper.insertForeach(propertyDeviceList);

        return new ResultBean<>();

    }

    @Override
    public ResultBean<PageInfo<?>> searchFurnitureListWithPropertyByPage(Integer pageNum, Integer pageSize, Integer categoryId, String furnitureName, Integer owner) {

        List<Device> deviceList = deviceMapper.searchFurnitureListWithPropertyList(categoryId, furnitureName, owner);
        PageInfo<Device> pageInfo = PageUtil.startPage(deviceList, pageNum, pageSize);
        return new ResultBean<>(pageInfo);

    }
}
