package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.CooperationMapper;
import com.technologygarden.entity.Cooperation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CooperationService")
public class CooperationServiceImpl implements CooperationService {
    private final CooperationMapper cooperationMapper;
    @Autowired
    public CooperationServiceImpl(CooperationMapper cooperationMapper) {
        this.cooperationMapper = cooperationMapper;
    }

    @Override
    public ResultBean<Page<Cooperation>> getCooperationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Cooperation> cooperation=cooperationMapper.getCooperationByPage(cId);
        return new ResultBean<>(cooperation);
    }

    @Override
    public ResultBean insertCooperation(Cooperation cooperation) {
        cooperation.setCId(cooperation.getInfoid());
        return new ResultBean(cooperationMapper.insert(cooperation));
    }

    @Override
    public ResultBean updateCooperation(Cooperation cooperation) {
        return new ResultBean(cooperationMapper.updateByPrimaryKey(cooperation));
    }

    @Override
    public ResultBean deleteCooperation(Integer id) {
        return new ResultBean(cooperationMapper.deleteByPrimaryKey(id));
    }
}
