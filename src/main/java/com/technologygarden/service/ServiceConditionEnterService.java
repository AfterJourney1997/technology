package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ServiceConditionEnterService {

    ResultBean<Page<ConditionEnter>> getConditionEnterListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertConditionEnter(ConditionEnter conditionEnter);

    ResultBean<?> deleteConditionEnterById(Integer conditionEnterId);

    ResultBean<?> updateConditionEnterById(ConditionEnter conditionEnter);

    ResultBean<Page<ConditionEnter>> searchConditionEnterListByPage(Integer pageNum, Integer pageSize, String title);
}
