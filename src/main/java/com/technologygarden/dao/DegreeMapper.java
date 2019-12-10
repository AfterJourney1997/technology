package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Degree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DegreeMapper {
    int deleteByPrimaryKey(Integer xId);

    int insert(Degree record);

    Degree selectByPrimaryKey(Integer xId);

    List<Degree> selectAll();

    Page<Degree> selectByPage();

    int updateByPrimaryKey(Degree record);

    Page<Degree> searchDegreeName(String degreeName);
}