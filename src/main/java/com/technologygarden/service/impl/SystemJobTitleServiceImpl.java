package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.JobTitleMapper;
import com.technologygarden.dao.LegalPersonMapper;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemJobTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("systemJobTitleService")
public class SystemJobTitleServiceImpl implements SystemJobTitleService {

    private final JobTitleMapper jobTitleMapper;
    private final LegalPersonMapper legalPersonMapper;

    @Autowired
    public SystemJobTitleServiceImpl(JobTitleMapper jobTitleMapper, LegalPersonMapper legalPersonMapper) {
        this.jobTitleMapper = jobTitleMapper;
        this.legalPersonMapper = legalPersonMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getSystemJobTitleListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<JobTitle> jobTitleList = jobTitleMapper.selectSystemJobTitleListByPage();
        PageInfo<?> pageInfo = new PageInfo<>(jobTitleList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertSystemJobTitle(JobTitle jobTitle) {

        jobTitleMapper.insert(jobTitle);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteSystemJobTitleById(Integer jobTitleId) {

        // 判断是否有法人在使用删除的职称
        List<LegalPerson> legalPersonList = legalPersonMapper.selectLegalPersonByJobTitleId(jobTitleId);
        if(legalPersonList.size() > 0){
            log.warn("删除职称中所删除的职称仍有法人在使用 ---> jobTitleId：" + jobTitleId);
            return new ResultBean<>(ResultStatus.DELETE_ERROR);
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
    public ResultBean<PageInfo<?>> searchSystemJobTitleListByPage(Integer pageNum, Integer pageSize, String jobTitle) {

        PageHelper.startPage(pageNum, pageSize);
        Page<JobTitle> jobTitleList = jobTitleMapper.searchSystemJobTitleListByPage(jobTitle);
        PageInfo<?> pageInfo = new PageInfo<>(jobTitleList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<List<JobTitle>> getAllSystemJobTitle() {

        List<JobTitle> jobTitles = jobTitleMapper.selectAll();
        return new ResultBean<>(jobTitles);
    }


}
