package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.OpinionMapper;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.OpinionService;
import org.springframework.stereotype.Service;

@Service("OpinionService")
public class OpinionServiceImpl implements OpinionService {
    private final OpinionMapper opinionMapper;

    public OpinionServiceImpl(OpinionMapper opinionMapper) {
        this.opinionMapper = opinionMapper;
    }

    @Override
    public ResultBean<Page<Opinion>> getOpinionByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Opinion> opinionList=opinionMapper.getOpinionByPage(cId);
        return new ResultBean<>(opinionList);
    }

    @Override
    public ResultBean insertOpinionByPage(Opinion opinion) {
        opinion.setCId(opinion.getRole().getInfoid());
        return new ResultBean(opinionMapper.insert(opinion));
    }

    @Override
    public ResultBean deleteOpinionByPage(Integer id) {
        return new ResultBean(opinionMapper.deleteByPrimaryKey(id));
    }
}
