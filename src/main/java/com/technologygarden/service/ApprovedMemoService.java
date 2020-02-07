package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ApprovedMemoService {
    ResultBean<PageInfo<?>> getApprovedMemoBycId(Integer pageNum, Integer pageSize,Integer cId);
    ResultBean<?> deleteApprovedMemoById(Integer id);
}
