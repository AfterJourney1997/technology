package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PoliticsStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PoliticsStatusMapper {
    int deleteByPrimaryKey(Integer zId);

    int insert(PoliticsStatus record);

    PoliticsStatus selectByPrimaryKey(Integer zId);

    List<PoliticsStatus> selectAll();

    Page<PoliticsStatus> selectByPage();

    int updateByPrimaryKey(PoliticsStatus record);

    Page<PoliticsStatus> searchPoliticsStatusByName(String politicsStatusName);
}