package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ConditionEnter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConditionEnterMapper {
    int deleteByPrimaryKey(Integer ceId);

    int insert(ConditionEnter record);

    ConditionEnter selectByPrimaryKey(Integer ceId);

    List<ConditionEnter> selectAll();

    int updateByPrimaryKey(ConditionEnter record);

    Page<ConditionEnter> selectConditionEnterListByPage();

    int updateConditionEnterByIdDynamic(ConditionEnter conditionEnter);

    Page<ConditionEnter> searchConditionEnterListByPage(@Param("title") String title);
}