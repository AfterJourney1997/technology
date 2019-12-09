package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;

public interface SystemActivityCategoryService {

    ResultBean<Page<ActivityCategory>> getSystemActivityCategoryListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertSystemActivityCategoryDynamic(ActivityCategory activityCategory);

    ResultBean<?> deleteSystemActivityCategoryById(Integer id);

    ResultBean<?> updateSystemActivityCategoryById(ActivityCategory activityCategory);

    ResultBean<Page<ActivityCategory>> searchSystemActivityCategoryByPage(Integer pageNum, Integer pageSize, String category);

    ResultBean<List<ActivityCategory>> getActivityCategoryList();
}
