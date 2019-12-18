package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceConditionEnterService {

    ResultBean<PageInfo<?>> getConditionEnterListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertConditionEnter(MultipartFile file, ConditionEnter conditionEnter);

    ResultBean<?> deleteConditionEnterById(Integer conditionEnterId);

    ResultBean<?> updateConditionEnterById(MultipartFile file, ConditionEnter conditionEnter);

    ResultBean<PageInfo<?>> searchConditionEnterListByPage(Integer pageNum, Integer pageSize, String title);
}
