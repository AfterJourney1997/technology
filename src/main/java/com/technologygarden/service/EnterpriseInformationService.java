package com.technologygarden.service;

import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.FileProductResultBean;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EnterpriseInformationService {
    ResultBean<?> updateByPrimaryKey(MultipartFile[] blFile,EnterpriseInformation enterpriseInformation) throws IOException;

    ResultBean<?> updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException;

    FileProductResultBean updateByFileProduct(Integer infoid, MultipartFile[] blFile) throws IOException;

    ResultBean<?> companyAnew(Integer infoid) throws IOException;

    ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer cId) throws IOException;

    ResultBean<List<EnterpriseInformation>> getEnterpriseInformationList();

    ResultBean<EnterpriseInformation> getEnterpriseInformationById(Integer cId);

    ResultBean<List<EnterpriseInformation>> getEnterpriseInformationListWithCommittee();

}
