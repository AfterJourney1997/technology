package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assetAssetCountService")
public class AssetAssetCountServiceImpl implements AssetAssetCountService {

    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;
    private final DeviceMapper deviceMapper;

    @Autowired
    public AssetAssetCountServiceImpl(CompanyRoomDeviceMapper companyRoomDeviceMapper, DeviceMapper deviceMapper) {
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public ResultBean<Page<CompanyRoomDevice>> getAssetCountByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.selectWithInfoByPage();
        return new ResultBean<>(companyRoomDevices);
    }

    @Override
    public ResultBean<Page<CompanyRoomDevice>> searchAssetCountByPage(Integer pageNum, Integer pageSize, String companyName, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.searchAssetCountByPage(companyName, roomName);
        return new ResultBean<>(companyRoomDevices);
    }

    @Override
    @Transactional
    public ResultBean<?> deleteAssetCount(CompanyRoomDevice companyRoomDevice) {

        // 首先删除资产分配表中数据
        companyRoomDeviceMapper.deleteByPrimaryKey(companyRoomDevice.getCrdId());

        // 再增加设备表中剩余数量
        Device device = deviceMapper.selectByPrimaryKey(companyRoomDevice.getCrdDeviceId());
        deviceMapper.updateByIdDynamic(Device.builder()
                .deviceId(companyRoomDevice.getCrdDeviceId())
                .remain(companyRoomDevice.getCrdNumber() + device.getRemain())
                .build());

        return new ResultBean<>();
    }


}
