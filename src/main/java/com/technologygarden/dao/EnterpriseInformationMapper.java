package com.technologygarden.dao;

import com.technologygarden.entity.EnterpriseInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface EnterpriseInformationMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(EnterpriseInformation record);

    EnterpriseInformation selectByPrimaryKey(Integer cId);

    List<EnterpriseInformation> selectAll();

    int updateByPrimaryKey(EnterpriseInformation record);
}