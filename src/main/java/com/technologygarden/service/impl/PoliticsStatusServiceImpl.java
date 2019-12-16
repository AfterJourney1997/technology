package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.PoliticsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PoliticsStatusService")
public class PoliticsStatusServiceImpl implements PoliticsStatusService {
    private final PoliticsStatusMapper politicsStatusMapper;
    private final EmployeeMapper employeeMapper;
    @Autowired
    public PoliticsStatusServiceImpl(PoliticsStatusMapper politicsStatusMapper, EmployeeMapper employeeMapper) {
        this.politicsStatusMapper = politicsStatusMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getPoliticsStatusByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<PoliticsStatus> politicsStatusList=politicsStatusMapper.selectByPage();
        PageInfo<?> pageInfo = new PageInfo<>(politicsStatusList);
        return new ResultBean<>(pageInfo);
    }
    @Override
    public ResultBean insertPoliticsStatus(PoliticsStatus politicsStatus) {
        return new ResultBean(politicsStatusMapper.insert(politicsStatus));
    }
    @Override
    public ResultBean updatePoliticsStatus(PoliticsStatus politicsStatus) {
        return new ResultBean(politicsStatusMapper.updateByPrimaryKey(politicsStatus));
    }

    @Override
    public ResultBean deletePoliticsStatus(Integer id) {
        //判断有无员工与要删除的政治面貌相关联
        List<Employee> employeeList=employeeMapper.selectByzId(id);
        if(employeeList.size()>0){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }
        return new ResultBean(politicsStatusMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<PageInfo<?>> searchPoliticsStatusByName(Integer pageNum, Integer pageSize, String politicsStatusName) {
        PageHelper.startPage(pageNum, pageSize);
        Page<PoliticsStatus> politicsStatusList=politicsStatusMapper.searchPoliticsStatusByName(politicsStatusName);
        PageInfo<?> pageInfo = new PageInfo<>(politicsStatusList);
        return new ResultBean<>(pageInfo);
    }
}
