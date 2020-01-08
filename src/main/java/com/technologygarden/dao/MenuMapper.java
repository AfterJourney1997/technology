package com.technologygarden.dao;

import com.technologygarden.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectAllMenuWithRightsByRoleId(@Param("roleId") Integer roleId);

}
