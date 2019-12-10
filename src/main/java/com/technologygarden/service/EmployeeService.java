package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface EmployeeService {
    public ResultBean<Page<Employee>> selectByPage(Integer pageNum, Integer pageSize,Integer cId);
    public ResultBean insertEmployee(Employee employee);
    public ResultBean updateEmployee(Employee employee);
    public ResultBean deleteEmployee(Integer eId);
    public ResultBean<Page<Employee>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName);
}
