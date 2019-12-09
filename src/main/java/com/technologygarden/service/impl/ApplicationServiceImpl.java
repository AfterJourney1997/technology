package com.technologygarden.service.impl;

import com.technologygarden.dao.ApplicationAdmissionMapper;
import com.technologygarden.entity.ApplicationAdmission;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ApplicationService")
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationAdmissionMapper applicationAdmissionMapper;
    @Autowired
    public ApplicationServiceImpl(ApplicationAdmissionMapper applicationAdmissionMapper) {
        this.applicationAdmissionMapper = applicationAdmissionMapper;
    }

    @Override
    public ResultBean updateByPrimaryKey(ApplicationAdmission record) {
        return new ResultBean(applicationAdmissionMapper.updateByPrimaryKey(record));
    }

    @Override
    public ResultBean<ApplicationAdmission> selectByPrimaryKey(Integer id) {
        return new ResultBean<>(applicationAdmissionMapper.selectByPrimaryKey(id));
    }
}
