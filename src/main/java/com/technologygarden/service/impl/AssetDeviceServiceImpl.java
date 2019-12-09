package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DeviceMapper;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assetDeviceService")
public class AssetDeviceServiceImpl implements AssetDeviceService {

    private final DeviceMapper deviceMapper;

    @Autowired
    public AssetDeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Override
    public ResultBean<Page<Device>> getDeviceListWithPropertyByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Device> deviceList = deviceMapper.selectDeviceListWithPropertyByPage();
        return new ResultBean<>(deviceList);

    }
}
