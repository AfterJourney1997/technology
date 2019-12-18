package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceActivityIncubationService {

    ResultBean<PageInfo<?>> getActivityIncubationListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertActivityIncubation(MultipartFile file, ActivityIncubation activityIncubation);

    ResultBean<?> deleteActivityIncubationById(Integer activityIncubationId);

    ResultBean<?> updateActivityIncubationById(MultipartFile file, ActivityIncubation activityIncubation);

    ResultBean<PageInfo<?>> searchActivityIncubationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName);
}
