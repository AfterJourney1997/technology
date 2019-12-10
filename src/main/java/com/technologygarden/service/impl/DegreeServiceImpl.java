package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DegreeService")
public class DegreeServiceImpl implements DegreeService {
    private final DegreeMapper degreeMapper;
    @Autowired
    public DegreeServiceImpl(DegreeMapper degreeMapper) {
        this.degreeMapper = degreeMapper;
    }

    @Override
    public ResultBean<Page<Degree>> getDegreeByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Degree> degreeList=degreeMapper.selectByPage();
        return new ResultBean<>(degreeList);
    }

    @Override
    public ResultBean insertDegree(Degree degree) {
        return new ResultBean(degreeMapper.insert(degree));
    }

    @Override
    public ResultBean updateDegree(Degree degree) {
        return new ResultBean(degreeMapper.updateByPrimaryKey(degree));
    }

    @Override
    public ResultBean deleteDegree(Integer id) {
        return new ResultBean(degreeMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<Page<Degree>> searchDegreeByName(Integer pageNum, Integer pageSize, String degreeName) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Degree> degreeList=degreeMapper.searchDegreeName(degreeName);
        return new ResultBean<>(degreeList);
    }
}
