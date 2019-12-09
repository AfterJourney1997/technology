package com.technologygarden.service.impl;

import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EnterpriseInformationService")
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    @Autowired
    public EnterpriseInformationServiceImpl(EnterpriseInformationMapper enterpriseInformationMapper) {
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) {
        return new ResultBean(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }
}
