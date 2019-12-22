package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.AssetAssetCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("assetAssetCountService")
public class AssetAssetCountServiceImpl implements AssetAssetCountService {

    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;
    private final DeviceMapper deviceMapper;

    @Autowired
    public AssetAssetCountServiceImpl(CompanyRoomDeviceMapper companyRoomDeviceMapper, DeviceMapper deviceMapper) {
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
        this.deviceMapper = deviceMapper;
    }

    // 分页获取设备分配信息
    @Override
    public ResultBean<PageInfo<?>> getAssetCountByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.selectWithInfoByPage();
        PageInfo<?> pageInfo = new PageInfo<>(companyRoomDevices);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<PageInfo<?>> searchAssetCountByPage(Integer pageNum, Integer pageSize, String companyName, String roomName, Integer categoryId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.searchAssetCountByPage(companyName, roomName, categoryId);
        PageInfo<?> pageInfo = new PageInfo<>(companyRoomDevices);
        return new ResultBean<>(pageInfo);
    }

    @Override
    @Transactional
    public ResultBean<?> deleteAssetCount(CompanyRoomDevice companyRoomDevice) {

        if (companyRoomDevice.getCrdId() == null || companyRoomDevice.getCrdDeviceId() == null || companyRoomDevice.getCrdNumber() == null) {
            log.warn("资产统计 回收资产参数缺失 ---> " + companyRoomDevice);
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

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
