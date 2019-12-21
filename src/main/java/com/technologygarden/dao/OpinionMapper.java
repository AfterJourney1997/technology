package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Opinion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OpinionMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBycId(Integer cId);

    int insert(Opinion record);

    Opinion selectByPrimaryKey(Integer id);

    List<Opinion> selectAll();

    Page<Opinion> getOpinionAllByPage();

    Page<Opinion> getOpinionByPage(Integer cId);

    int updateByPrimaryKey(Opinion record);
}