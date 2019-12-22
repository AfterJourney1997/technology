package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.PowerLoadMapper;
import com.technologygarden.entity.PowerLoad;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.GardenManagePowerLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("gardenManagePowerLoadService")
public class GardenManagePowerLoadServiceImpl implements GardenManagePowerLoadService {

    private final PowerLoadMapper powerLoadMapper;

    @Autowired
    public GardenManagePowerLoadServiceImpl(PowerLoadMapper powerLoadMapper) {
        this.powerLoadMapper = powerLoadMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getPowerLoadListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PowerLoad> powerLoadList = powerLoadMapper.selectAllWithRoomInfoByPage();
        PageInfo<?> pageInfo = new PageInfo<>(powerLoadList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertPowerLoad(PowerLoad powerLoad) {

        powerLoadMapper.insert(powerLoad);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deletePowerLoadById(Integer powerLoadId) {

        powerLoadMapper.deleteByPrimaryKey(powerLoadId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updatePowerLoadById(PowerLoad powerLoad) {

        if (powerLoad.getPlId() == null) {
            log.warn("修改用电负荷 主键缺失 ---> " + powerLoad);
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        powerLoadMapper.updateDynamicById(powerLoad);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchPowerLoad(Integer pageNum, Integer pageSize, Integer roomId, Integer buildingId, String companyName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PowerLoad> powerLoadList = powerLoadMapper.searchPowerLoadWithRoomInfoByPage(roomId, buildingId, companyName);
        PageInfo<?> pageInfo = new PageInfo<>(powerLoadList);
        return new ResultBean<>(pageInfo);

    }

}
