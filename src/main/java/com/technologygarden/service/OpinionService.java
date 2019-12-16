package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface OpinionService {
    public ResultBean<PageInfo<?>> getOpinionByPage(Integer pageNum, Integer pageSize, Integer cId);
    public ResultBean insertOpinionByPage(Opinion opinion);
    public ResultBean deleteOpinionByPage(Integer id);
}
