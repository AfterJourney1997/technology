package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final DegreeMapper degreeMapper;
    private final PoliticsStatusMapper politicsStatusMapper;
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, DegreeMapper degreeMapper, PoliticsStatusMapper politicsStatusMapper) {
        this.employeeMapper = employeeMapper;
        this.degreeMapper = degreeMapper;
        this.politicsStatusMapper = politicsStatusMapper;
    }

    @Override
    public ResultBean<Page<Employee>> selectByPage(Integer pageNum, Integer pageSize,Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByPage(cId);
        for(Employee employee:employeeList){
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
        }
        return new ResultBean<>(employeeList);
    }

    @Override
    public ResultBean insertEmployee(Employee employee) {
        return new ResultBean(employeeMapper.insert(employee));
    }

    @Override
    public ResultBean updateEmployee(Employee employee) {
        return new ResultBean(employeeMapper.updateByPrimaryKey(employee));
    }

    @Override
    public ResultBean deleteEmployee(Integer eId) {
        return new ResultBean(employeeMapper.deleteByPrimaryKey(eId));
    }
    @Override
    public ResultBean<Page<Employee>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) {
        System.out.println("pageNum:"+pageNum+"pageSize:"+pageSize+"cId:"+cId+"employeeName:"+employeeName);
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByNamePage(cId,employeeName);
        for(Employee employee:employeeList){
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
        }
        return new ResultBean<>(employeeList);
    }
}
