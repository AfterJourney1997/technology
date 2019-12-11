package com.technologygarden.service.impl;

import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("EnterpriseInformationService")
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {
    private final EnterpriseInformationMapper enterpriseInformationMapper;

    @Autowired
    public EnterpriseInformationServiceImpl(EnterpriseInformationMapper enterpriseInformationMapper) {
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    //入住申请提交
    @Override
    public ResultBean<?> updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException {
        String UUName = FilUploadUtils.saveFile(enterpriseInformation.getBlFile());
        enterpriseInformation.setFileName(UUName);
        enterpriseInformation.setFileName(enterpriseInformation.getBlFile().getOriginalFilename());
        enterpriseInformation.setCId(enterpriseInformation.getInfoid());
        System.out.println(enterpriseInformation);
        return new ResultBean<>(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }

    @Override
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer info) throws IOException {
        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(info);
        enterpriseInformation.setFilePath(FilUploadUtils.getFilePath() + "\\" + enterpriseInformation.getFileName());
        enterpriseInformation.setFileName(FilUploadUtils.getfileName(enterpriseInformation.getFileName()));
        return new ResultBean<>(enterpriseInformation);
    }

    @Override
    public ResultBean<?> updateByPrimaryKey(EnterpriseInformation enterpriseInformation) throws IOException {
        String UUName = FilUploadUtils.saveFile(enterpriseInformation.getBlFile());//保存文件
        Integer infoid = enterpriseInformation.getInfoid();
        EnterpriseInformation enterprise = enterpriseInformationMapper.selectByPrimaryKey(infoid);
        enterpriseInformation.setFileName(UUName);//获取文件名
        enterprise.setCName(enterpriseInformation.getCName());//企业名称
        enterprise.setCCategory(enterpriseInformation.getCCategory());//企业类别
        enterprise.setCLegalperson(enterpriseInformation.getCLegalperson());//法人
        enterprise.setCDate(enterpriseInformation.getCDate());//注册时间
        enterprise.setCFund(enterpriseInformation.getCFund());//注册资金
        enterprise.setCProduct(enterpriseInformation.getCProduct());//主要产品
        enterprise.setCEmployee(enterpriseInformation.getCEmployee());//入职员工数
        enterprise.setCInformation(enterpriseInformation.getCInformation());//员工信息
        enterprise.setFileName(enterpriseInformation.getFileName());//附件名
        return new ResultBean<>(enterpriseInformationMapper.updateByPrimaryKey(enterprise));
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getEnterpriseInformationList() {

        List<EnterpriseInformation> enterpriseInformations = enterpriseInformationMapper.selectAll();
        return new ResultBean<>(enterpriseInformations);
    }
}
