package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer eId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer eId);

    List<Employee> selectAll();

    List<Employee> selectByxId(Integer xId);

    List<Employee> selectByzId(Integer zId);

    Page<Employee> selectByNamePage(@Param("cId") Integer cId, @Param("employeeName") String employeeName);

    Page<Employee> selectByPage(Integer cId);

    int updateByPrimaryKey(Employee record);
}