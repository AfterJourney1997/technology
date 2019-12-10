package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;


public interface DeclareAwardService {
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(Integer pageNum, Integer pageSize,Integer cId);
    public ResultBean insertDeclareAward(DeclareAward declareAward);
}
