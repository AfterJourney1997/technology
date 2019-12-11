package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.io.IOException;

public interface EmployeeService {
    public ResultBean<Page<Employee>> selectByPage(Integer pageNum, Integer pageSize,Integer cId) throws IOException;
    public ResultBean insertEmployee(Employee employee) throws IOException;
    public ResultBean updateEmployee(Employee employee) throws IOException;
    public ResultBean deleteEmployee(Integer eId) throws IOException;
    public ResultBean<Page<Employee>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) throws IOException;
}
