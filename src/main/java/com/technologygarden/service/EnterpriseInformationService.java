package com.technologygarden.service;

import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import io.swagger.models.auth.In;

import java.io.IOException;

public interface EnterpriseInformationService {

    ResultBean<?> updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException;

    ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer cId) throws IOException;

    ResultBean<?> updateByPrimaryKey(EnterpriseInformation enterpriseInformation) throws IOException;

}
