package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("assetAssetCountService")
public class AssetAssetCountServiceImpl implements AssetAssetCountService {

    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;

    @Autowired
    public AssetAssetCountServiceImpl(CompanyRoomDeviceMapper companyRoomDeviceMapper) {
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
    }

    @Override
    public ResultBean<Page<CompanyRoomDevice>> getAssetCountByPage(Integer pageNum, Integer pageSize) {

/*        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDevices = companyRoomDeviceMapper.selectWithInfoByPage();
        return new ResultBean<>(companyRoomDevices);*/
        return null;
    }


}
