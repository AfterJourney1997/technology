package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.BuildingMapper;
import com.technologygarden.dao.ParkEnvironmentMapper;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ParkEnvironment;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ParkEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ParkEnvironmentService")
public class ParkEnvironmentServiceImpl implements ParkEnvironmentService {
    private final ParkEnvironmentMapper parkEnvironmentMapper;
    private final BuildingMapper buildingMapper;
    @Autowired
    public ParkEnvironmentServiceImpl(ParkEnvironmentMapper parkEnvironmentMapper, BuildingMapper buildingMapper) {
        this.parkEnvironmentMapper = parkEnvironmentMapper;
        this.buildingMapper = buildingMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getParkEnvironmentByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ParkEnvironment> opinionList=parkEnvironmentMapper.getParkEnvironmentByPage();
        for (ParkEnvironment parkEnvironment:opinionList){
            Building building=buildingMapper.selectByPrimaryKey(parkEnvironment.getBuildingid());
            if(building!=null){
                parkEnvironment.setBuildingName(building.getBuildingName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(opinionList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<Building>> getAllBuilding() {
        return new ResultBean<>(buildingMapper.selectAll());
    }

    @Override
    public ResultBean insertParkEnvironment(ParkEnvironment parkEnvironment) {
        return new ResultBean(parkEnvironmentMapper.insert(parkEnvironment));
    }

    @Override
    public ResultBean updateParkEnvironment(ParkEnvironment parkEnvironment) {
        return new ResultBean(parkEnvironmentMapper.updateByPrimaryKey(parkEnvironment));
    }

    @Override
    public ResultBean deleteParkEnvironment(Integer id) {
        return new ResultBean(parkEnvironmentMapper.deleteByPrimaryKey(id));
    }
}
