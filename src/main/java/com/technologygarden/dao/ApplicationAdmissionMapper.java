package com.technologygarden.dao;

import com.technologygarden.entity.ApplicationAdmission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ApplicationAdmissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplicationAdmission record);

    ApplicationAdmission selectByPrimaryKey(Integer id);

    List<ApplicationAdmission> selectAll();

    int updateByPrimaryKey(ApplicationAdmission record);
}