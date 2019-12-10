package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface SystemJobTitleService {

    ResultBean<Page<JobTitle>> getSystemJobTitleListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertSystemJobTitle(JobTitle jobTitle);

    ResultBean<?> deleteSystemJobTitleById(Integer jobTitleId);

    ResultBean<?> updateSystemJobTitleById(JobTitle jobTitle);

    ResultBean<Page<JobTitle>> searchSystemJobTitleListByPage(Integer pageNum, Integer pageSize, String jobTitle);
}
