package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.PoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PoliticsStatusService")
public class PoliticsStatusServiceImpl implements PoliticsStatusService {
    private final PoliticsStatusMapper politicsStatusMapper;
    @Autowired
    public PoliticsStatusServiceImpl(PoliticsStatusMapper politicsStatusMapper) {
        this.politicsStatusMapper = politicsStatusMapper;
    }

    @Override
    public ResultBean<Page<PoliticsStatus>> getPoliticsStatusByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<PoliticsStatus> politicsStatusList=politicsStatusMapper.selectByPage();
        return new ResultBean<>(politicsStatusList);
    }
    @Override
    public ResultBean insertPoliticsStatus(PoliticsStatus politicsStatus) {
        return new ResultBean(politicsStatusMapper.insert(politicsStatus));
    }
    @Override
    public ResultBean updatePoliticsStatus(PoliticsStatus politicsStatus) {
        return new ResultBean(politicsStatusMapper.updateByPrimaryKey(politicsStatus));
    }

    @Override
    public ResultBean deletePoliticsStatus(Integer id) {
        return new ResultBean(politicsStatusMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<Page<PoliticsStatus>> searchPoliticsStatusByName(Integer pageNum, Integer pageSize, String politicsStatusName) {
        PageHelper.startPage(pageNum, pageSize);
        Page<PoliticsStatus> politicsStatusList=politicsStatusMapper.searchPoliticsStatusByName(politicsStatusName);
        return new ResultBean<>(politicsStatusList);
    }
}
