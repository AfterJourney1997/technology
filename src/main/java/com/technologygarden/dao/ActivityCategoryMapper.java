package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityCategoryMapper {
    int deleteByPrimaryKey(Integer acId);

    int insert(ActivityCategory record);

    ActivityCategory selectByPrimaryKey(Integer acId);

    List<ActivityCategory> selectAll();

    int updateByPrimaryKey(ActivityCategory record);

    Page<ActivityCategory> selectSystemActivityCategoryListByPage();

    Page<ActivityCategory> searchSystemActivityCategoryByPage(@Param("category") String category);
}