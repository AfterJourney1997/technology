package com.technologygarden.dao;

import com.technologygarden.entity.RoleRights;
import java.util.List;

public interface RoleRightsMapper {
    int deleteByPrimaryKey(Integer rrId);

    int insert(RoleRights record);

    RoleRights selectByPrimaryKey(Integer rrId);

    List<RoleRights> selectAll();

    int updateByPrimaryKey(RoleRights record);
}