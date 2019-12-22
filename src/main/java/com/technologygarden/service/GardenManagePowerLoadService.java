package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PowerLoad;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface GardenManagePowerLoadService {

    ResultBean<PageInfo<?>> getPowerLoadListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertPowerLoad(PowerLoad powerLoad);

    ResultBean<?> deletePowerLoadById(Integer powerLoadId);

    ResultBean<?> updatePowerLoadById(PowerLoad powerLoad);

    ResultBean<PageInfo<?>> searchPowerLoad(Integer pageNum, Integer pageSize, Integer roomId, Integer buildingId, String companyName);
}
