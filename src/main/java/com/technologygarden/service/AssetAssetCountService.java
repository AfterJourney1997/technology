package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AssetAssetCountService {

    ResultBean<Page<CompanyRoomDevice>> getAssetCountByPage(Integer pageNum, Integer pageSize);

}
