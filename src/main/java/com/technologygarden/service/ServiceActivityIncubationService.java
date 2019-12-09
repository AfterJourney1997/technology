package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;
import lombok.NonNull;

public interface ServiceActivityIncubationService {

    ResultBean<Page<ActivityIncubation>> getActivityIncubationListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertActivityIncubation(ActivityIncubation activityIncubation);

    ResultBean<?> deleteActivityIncubationById(Integer activityIncubationId);

    ResultBean<?> updateActivityIncubationById(ActivityIncubation activityIncubation);

    ResultBean<Page<ActivityIncubation>> searchActivityIncubationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName);
}
