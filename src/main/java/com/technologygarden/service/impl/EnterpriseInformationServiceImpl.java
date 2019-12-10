package com.technologygarden.service.impl;

import com.technologygarden.dao.ApplicationAdmissionMapper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.ApplicationAdmission;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("EnterpriseInformationService")
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    private final ApplicationAdmissionMapper applicationAdmissionMapper;
    @Autowired
    public EnterpriseInformationServiceImpl(EnterpriseInformationMapper enterpriseInformationMapper, ApplicationAdmissionMapper applicationAdmissionMapper) {
        this.enterpriseInformationMapper = enterpriseInformationMapper;
        this.applicationAdmissionMapper = applicationAdmissionMapper;
    }

    @Override
    public ResultBean updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException {
        FilUploadUtils.saveFile(enterpriseInformation.getBlFile());
        enterpriseInformation.setFileName(enterpriseInformation.getBlFile().getOriginalFilename());
        return new ResultBean(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }

    @Override
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Role role) {
        ApplicationAdmission appl= applicationAdmissionMapper.selectByPrimaryKey(role.getInfoid());
        EnterpriseInformation ente=enterpriseInformationMapper.selectByPrimaryKey(role.getInfoid());
        ente.setEName(appl.getCName());
        ente.setECategory(appl.getCCategory());
        ente.setEDate(appl.getCDate());
        ente.setEFund(appl.getCFund());
        ente.setEProduct(appl.getCProduct());
        return new ResultBean<>(ente);
    }
}
