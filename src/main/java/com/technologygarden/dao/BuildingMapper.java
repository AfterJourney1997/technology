package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Building;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    Building selectByPrimaryKey(Integer id);

    List<Building> selectAll();

    int updateByPrimaryKey(Building record);

    Page<Building> selectByPage();

    int insertBuildingForeach(List<Building> buildingList);

    int updateBuildingById(Building building);

    Page<Building> searchBuildingByName(String buildingName);
}