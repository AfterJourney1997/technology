package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Maintain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MaintainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Maintain record);

    Maintain selectByPrimaryKey(Integer id);

    List<Maintain> selectAll();

    Page<Maintain> getMaintainByPage();

    Page<Maintain> searchMaintainByName(String maintainName);

    int updateByPrimaryKey(Maintain record);
}