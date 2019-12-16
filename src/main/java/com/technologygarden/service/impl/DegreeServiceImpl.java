package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.LegalPersonMapper;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DegreeService")
public class DegreeServiceImpl implements DegreeService {
    private final DegreeMapper degreeMapper;
    private final EmployeeMapper employeeMapper;
    private final LegalPersonMapper legalPersonMapper;
    @Autowired
    public DegreeServiceImpl(DegreeMapper degreeMapper, EmployeeMapper employeeMapper, LegalPersonMapper legalPersonMapper) {
        this.degreeMapper = degreeMapper;
        this.employeeMapper = employeeMapper;
        this.legalPersonMapper = legalPersonMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getDegreeByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Degree> degreeList=degreeMapper.selectByPage();
        PageInfo<?> pageInfo = new PageInfo<>(degreeList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertDegree(Degree degree) {
        return new ResultBean(degreeMapper.insert(degree));
    }

    @Override
    public ResultBean updateDegree(Degree degree) {
        return new ResultBean(degreeMapper.updateByPrimaryKey(degree));
    }

    @Override
    public ResultBean<?> deleteDegree(Integer id) {
        List<Employee> employeeList =employeeMapper.selectByxId(id);
        List<LegalPerson> legalPeople =legalPersonMapper.selectLegalPersonByDegreeId(id);
        //判断有无员工与学位关联
        if(employeeList.size()>0 || legalPeople.size()>0){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }
        return new ResultBean<>(degreeMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<PageInfo<?>> searchDegreeByName(Integer pageNum, Integer pageSize, String degreeName) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Degree> degreeList=degreeMapper.searchDegreeName(degreeName);
        PageInfo<?> pageInfo = new PageInfo<>(degreeList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<Degree>> getAllDegree() {

        List<Degree> degrees = degreeMapper.selectAll();
        return new ResultBean<>(degrees);
    }
}
