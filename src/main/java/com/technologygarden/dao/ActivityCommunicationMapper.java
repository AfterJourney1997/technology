package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityCommunication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityCommunicationMapper {
    int deleteByPrimaryKey(Integer acomId);

    int insert(ActivityCommunication record);

    ActivityCommunication selectByPrimaryKey(Integer acomId);

    List<ActivityCommunication> selectAll();

    int updateByPrimaryKey(ActivityCommunication record);

    Page<ActivityCommunication> selectAllWithCategoryByPage();

    int updateDynamicById(ActivityCommunication activityCommunication);

    Page<ActivityCommunication> searchByPage(@Param("activityCategoryId") Integer activityCategoryId, @Param("activityName") String activityName);
}