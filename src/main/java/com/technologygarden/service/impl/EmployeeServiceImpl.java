package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EmployeeService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public ResultBean<Page<Employee>> selectByPage(Integer pageNum, Integer pageSize,Integer cId) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByPage(cId);
        for(Employee employee:employeeList){
            String filePath=FilUploadUtils.getFilePath()+"\\"+employee.getFileName();
            employee.setFilePath(filePath);
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
        }
        return new ResultBean<>(employeeList);
    }

    @Override
    public ResultBean insertEmployee(Employee employee) throws IOException {
        FilUploadUtils.saveFile(employee.getBlFile());
        employee.setFileName(employee.getBlFile().getOriginalFilename());
        employee.setCId(employee.getRole().getInfoid());
        return new ResultBean(employeeMapper.insert(employee));
    }

    @Override
    public ResultBean updateEmployee(Employee employee) throws IOException {
        FilUploadUtils.saveFile(employee.getBlFile());
        employee.setFileName(employee.getBlFile().getOriginalFilename());
        return new ResultBean(employeeMapper.updateByPrimaryKey(employee));
    }

    @Override
    public ResultBean deleteEmployee(Integer eId) {
        return new ResultBean(employeeMapper.deleteByPrimaryKey(eId));
    }
    @Override
    public ResultBean<Page<Employee>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) throws IOException {
        System.out.println("pageNum:"+pageNum+"pageSize:"+pageSize+"cId:"+cId+"employeeName:"+employeeName);
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByNamePage(cId,employeeName);
        for(Employee employee:employeeList){
            String filePath=FilUploadUtils.getFilePath()+"\\"+employee.getFileName();
            employee.setFilePath(filePath);
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
        }
        return new ResultBean<>(employeeList);
    }
}
