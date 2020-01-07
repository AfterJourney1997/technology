package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;

public interface AssetBuildingService {


    ResultBean<PageInfo<?>> getBuildingByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertBuildingForeach(List<Building> buildingList);

    ResultBean<?> deleteBuildingById(Integer buildingId);

    ResultBean<?> updateBuildingById(Building building);

    ResultBean<PageInfo<?>> searchBuildingByName(Integer pageNum, Integer pageSize, String buildingName);

    ResultBean<List<Building>> getAllBuilding();

    ResultBean<List<Building>> getBuildingById(Integer buildingId);
}
