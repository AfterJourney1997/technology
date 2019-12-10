package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.JobTitle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobTitleMapper {
    int deleteByPrimaryKey(Integer jtId);

    int insert(JobTitle record);

    JobTitle selectByPrimaryKey(Integer jtId);

    List<JobTitle> selectAll();

    int updateByPrimaryKey(JobTitle record);

    Page<JobTitle> selectSystemJobTitleListByPage();

    int updateByIdDynamic(JobTitle jobTitle);

    Page<JobTitle> searchSystemJobTitleListByPage(@Param("jobTitle") String jobTitle);
}