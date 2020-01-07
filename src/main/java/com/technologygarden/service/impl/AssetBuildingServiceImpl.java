package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.BuildingMapper;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assetBuildingService")
public class AssetBuildingServiceImpl implements AssetBuildingService {

    private final BuildingMapper buildingMapper;

    @Autowired
    public AssetBuildingServiceImpl(BuildingMapper buildingMapper) {
        this.buildingMapper = buildingMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getBuildingByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Building> buildingList = buildingMapper.selectByPage();
        PageInfo<?> page = new PageInfo<>(buildingList);
        return new ResultBean<>(page);
    }

    @Override
    public ResultBean<?> insertBuildingForeach(List<Building> buildingList) {
        buildingMapper.insertBuildingForeach(buildingList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteBuildingById(Integer buildingId) {
        buildingMapper.deleteByPrimaryKey(buildingId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateBuildingById(Building building) {
        buildingMapper.updateBuildingById(building);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchBuildingByName(Integer pageNum, Integer pageSize, String buildingName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Building> buildingList = buildingMapper.searchBuildingByName(buildingName);
        PageInfo<?> page = new PageInfo<>(buildingList);
        return new ResultBean<>(page);

    }

    @Override
    public ResultBean<List<Building>> getAllBuilding() {

        List<Building> buildingList = buildingMapper.selectAll();
        return new ResultBean<>(buildingList);
    }

    @Override
    public ResultBean<List<Building>> getBuildingById(Integer buildingId) {
        List<Building> buildingList = buildingMapper.getBuildingById(buildingId);
        return new ResultBean<>(buildingList);
    }
}
