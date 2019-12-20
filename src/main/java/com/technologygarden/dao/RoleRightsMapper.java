package com.technologygarden.dao;

import com.technologygarden.entity.RoleRights;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleRightsMapper {
    int deleteByPrimaryKey(Integer rrId);

    int insert(RoleRights record);

    RoleRights selectByPrimaryKey(Integer rrId);

    List<RoleRights> selectAll();

    int updateByPrimaryKey(RoleRights record);

    int deleteByRoleId(Integer id);

    int insertForeach(@Param("id") Integer id, @Param("rightsList") List<Integer> rightsList);
}