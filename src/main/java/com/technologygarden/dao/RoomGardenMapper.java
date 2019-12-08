package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.RoomGarden;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoomGardenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoomGarden record);

    RoomGarden selectByPrimaryKey(Integer id);

    Page<RoomGarden> selectAll();

    int updateByPrimaryKey(RoomGarden record);
}