package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.io.IOException;


public interface DeclareAwardService {
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(Integer pageNum, Integer pageSize,Integer cId) throws IOException;
    public ResultBean insertDeclareAward(DeclareAward declareAward);
}
