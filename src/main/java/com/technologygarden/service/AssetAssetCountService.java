package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AssetAssetCountService {

    ResultBean<PageInfo<?>> getAssetCountByPage(Integer pageNum, Integer pageSize);

    ResultBean<PageInfo<?>> searchAssetCountByPage(Integer pageNum, Integer pageSize, String companyName, String roomName, Integer categoryId);

    ResultBean<?> deleteAssetCount(CompanyRoomDevice companyRoomDevice);
}
