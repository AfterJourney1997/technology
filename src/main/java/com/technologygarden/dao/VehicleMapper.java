package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface VehicleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBycId(Integer cId);

    int insert(Vehicle record);

    Vehicle selectByPrimaryKey(Integer id);

    List<Vehicle> selectAll();

    Page<Vehicle> selectVehicleByPage();

    Page<Vehicle> searchVehicleByNumberPlate(String search);

    int updateByPrimaryKey(Vehicle record);
}