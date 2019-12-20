package com.technologygarden.dao;

import com.technologygarden.entity.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectAllMenuWithRights();

}
