package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.AwardsMapper;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AwardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AwardsService")
public class AwardsServiceImpl implements AwardsService {
    private final AwardsMapper awardsMapper;
    @Autowired
    public AwardsServiceImpl(AwardsMapper awardsMapper) {
        this.awardsMapper = awardsMapper;
    }

    @Override
    public ResultBean<Page<Awards>> getAwardsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Awards> awardsList=awardsMapper.selectByPage();
        return new ResultBean<>(awardsList);
    }

    @Override
    public ResultBean insertAwards(Awards awards) {
        return new ResultBean(awardsMapper.insert(awards));
    }

    @Override
    public ResultBean updateAwards(Awards awards) {
        return new ResultBean(awardsMapper.updateByPrimaryKey(awards));
    }

    @Override
    public ResultBean deleteAwards(Integer id) {
        return new ResultBean(awardsMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<Page<Awards>> searchAwardsName(Integer pageNum, Integer pageSize,String awardsName) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Awards> awardsList=awardsMapper.searchAwardsName(awardsName);
        return new ResultBean<>(awardsList);
    }
}
