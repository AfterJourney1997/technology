package com.technologygarden.dao;

import com.technologygarden.entity.Rights;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RightsMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(Rights record);

    Rights selectByPrimaryKey(Integer rId);

    List<Rights> selectAll();

    int updateByPrimaryKey(Rights record);

    List<Rights> selectRightsByRoleId(@Param("roleId") Integer roleId);
}