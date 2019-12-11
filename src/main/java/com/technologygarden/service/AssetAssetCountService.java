package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AssetAssetCountService {

    ResultBean<Page<CompanyRoomDevice>> getAssetCountByPage(Integer pageNum, Integer pageSize);

    ResultBean<Page<CompanyRoomDevice>> searchAssetCountByPage(Integer pageNum, Integer pageSize, String companyName, String roomName);

    ResultBean<?> deleteAssetCount(CompanyRoomDevice companyRoomDevice);
}
