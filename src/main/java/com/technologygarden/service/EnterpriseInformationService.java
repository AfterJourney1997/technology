package com.technologygarden.service;

import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;

import java.io.IOException;

public interface EnterpriseInformationService {
    public ResultBean updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException;
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Role role);
}
