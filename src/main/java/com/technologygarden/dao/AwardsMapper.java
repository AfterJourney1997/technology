package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Awards;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface AwardsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Awards record);

    Awards selectByPrimaryKey(Integer id);

    List<Awards> selectAll();

    Page<Awards> selectByPage();

    Page<Awards> searchAwardsName(String awardsName);

    int updateByPrimaryKey(Awards record);
}