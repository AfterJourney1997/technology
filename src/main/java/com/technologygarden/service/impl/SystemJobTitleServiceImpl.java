package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.JobTitleMapper;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemJobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemJobTitleService")
public class SystemJobTitleServiceImpl implements SystemJobTitleService {

    private final JobTitleMapper jobTitleMapper;

    @Autowired
    public SystemJobTitleServiceImpl(JobTitleMapper jobTitleMapper) {
        this.jobTitleMapper = jobTitleMapper;
    }

    @Override
    public ResultBean<Page<JobTitle>> getSystemJobTitleListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<JobTitle> jobTitleList = jobTitleMapper.selectSystemJobTitleListByPage();
        return new ResultBean<>(jobTitleList);
    }

    @Override
    public ResultBean<?> insertSystemJobTitle(JobTitle jobTitle) {

        jobTitleMapper.insert(jobTitle);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteSystemJobTitleById(Integer jobTitleId) {

        // 判断是否有法人在使用删除的职称
        if(1 != 1){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }

        jobTitleMapper.deleteByPrimaryKey(jobTitleId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateSystemJobTitleById(JobTitle jobTitle) {

        jobTitleMapper.updateByPrimaryKey(jobTitle);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<JobTitle>> searchSystemJobTitleListByPage(Integer pageNum, Integer pageSize, String jobTitle) {

        PageHelper.startPage(pageNum, pageSize);
        Page<JobTitle> jobTitleList = jobTitleMapper.searchSystemJobTitleListByPage(jobTitle);
        return new ResultBean<>(jobTitleList);

    }


}
