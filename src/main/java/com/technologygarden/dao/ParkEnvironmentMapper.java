package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ParkEnvironment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ParkEnvironmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkEnvironment record);

    ParkEnvironment selectByPrimaryKey(Integer id);

    List<ParkEnvironment> selectAll();

    Page<ParkEnvironment> getParkEnvironmentByPage();

    int updateByPrimaryKey(ParkEnvironment record);
}