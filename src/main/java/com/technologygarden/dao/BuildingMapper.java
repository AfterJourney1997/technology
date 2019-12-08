package com.technologygarden.dao;

import com.technologygarden.entity.Building;
import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    Building selectByPrimaryKey(Integer id);

    List<Building> selectAll();

    int updateByPrimaryKey(Building record);
}