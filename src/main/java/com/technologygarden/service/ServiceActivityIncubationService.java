package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ServiceActivityIncubationService {

    ResultBean<PageInfo<?>> getActivityIncubationListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertActivityIncubation(ActivityIncubation activityIncubation);

    ResultBean<?> deleteActivityIncubationById(Integer activityIncubationId);

    ResultBean<?> updateActivityIncubationById(ActivityIncubation activityIncubation);

    ResultBean<PageInfo<?>> searchActivityIncubationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName);
}
