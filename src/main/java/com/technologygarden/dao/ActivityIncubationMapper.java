package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityIncubation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityIncubationMapper {
    int deleteByPrimaryKey(Integer aiId);

    int insert(ActivityIncubation record);

    ActivityIncubation selectByPrimaryKey(Integer aiId);

    List<ActivityIncubation> selectAll();

    int updateByPrimaryKey(ActivityIncubation record);

    Page<ActivityIncubation> selectActivityIncubationListByPage();

    int updateByIdDynamic(ActivityIncubation activityIncubation);

    Page<ActivityIncubation> searchActivityIncubationListByPage(@Param("activityCategoryId") Integer activityCategoryId, @Param("activityName") String activityName);
}