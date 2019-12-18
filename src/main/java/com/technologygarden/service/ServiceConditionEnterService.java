package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ServiceConditionEnterService {

    ResultBean<PageInfo<?>> getConditionEnterListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertConditionEnter(ConditionEnter conditionEnter);

    ResultBean<?> deleteConditionEnterById(Integer conditionEnterId);

    ResultBean<?> updateConditionEnterById(ConditionEnter conditionEnter);

    ResultBean<PageInfo<?>> searchConditionEnterListByPage(Integer pageNum, Integer pageSize, String title);
}
