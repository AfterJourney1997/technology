package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface OpinionService {
    public ResultBean<Page<Opinion>> getOpinionByPage(Integer pageNum, Integer pageSize, Integer cId);
    public ResultBean insertOpinionByPage(Opinion opinion);
    public ResultBean deleteOpinionByPage(Integer id);
}
