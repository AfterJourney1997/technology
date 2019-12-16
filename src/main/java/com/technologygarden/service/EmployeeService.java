package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeService {
    public ResultBean<PageInfo<?>> selectByPage(Integer pageNum, Integer pageSize, Integer cId) throws IOException;
    public ResultBean<PoliticsStatus> selectAllByPoliticsStatus();
    public ResultBean<Degree> selectAllByDegree();
    public ResultBean insertEmployee(MultipartFile blFile[], Employee employee) throws IOException;
    public ResultBean updateEmployee(MultipartFile blFile[], Employee employee) throws IOException;
    public ResultBean deleteEmployee(Integer eId) throws IOException;
    public ResultBean<PageInfo<?>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) throws IOException;
}
