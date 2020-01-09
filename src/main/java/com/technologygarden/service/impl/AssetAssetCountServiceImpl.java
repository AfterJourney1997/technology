package com.technologygarden.service.impl;

import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.AssetAssetCountService;
import com.technologygarden.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        List<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.selectWithInfoList();
        PageInfo<CompanyRoomDevice> pageInfo = PageUtil.startPage(companyRoomDevices, pageNum, pageSize);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<PageInfo<?>> searchAssetCountByPage(Integer pageNum, Integer pageSize, String companyName, String roomName, Integer categoryId) {

        List<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.searchAssetCountList(companyName, roomName, categoryId);
        PageInfo<CompanyRoomDevice> pageInfo = PageUtil.startPage(companyRoomDevices, pageNum, pageSize);
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
