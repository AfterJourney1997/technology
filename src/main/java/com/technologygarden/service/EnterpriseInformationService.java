package com.technologygarden.service;

import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import io.swagger.models.auth.In;

import java.io.IOException;

public interface EnterpriseInformationService {
    public ResultBean updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException;
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer cId) throws IOException;
    public ResultBean updateByPrimaryKey(EnterpriseInformation enterpriseInformation);
}
