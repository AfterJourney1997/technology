package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ServiceApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface ServiceApplicationMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBycId(Integer cId);

    int insert(ServiceApplication record);

    ServiceApplication selectByPrimaryKey(Integer id);

    List<ServiceApplication> selectAll();

    Page<ServiceApplication> getAllServiceApplication();

    List<ServiceApplication> selectBymaintainId(Integer maintainId);

    Page<ServiceApplication> getServiceApplicationByPage(Integer cId);

    int updateByPrimaryKey(ServiceApplication record);
}