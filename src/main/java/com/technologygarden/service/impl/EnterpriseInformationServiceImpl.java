package com.technologygarden.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.JobTitleMapper;
import com.technologygarden.dao.LegalPersonMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("EnterpriseInformationService")
public class EnterpriseInformationServiceImpl implements EnterpriseInformationService {
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    private final LegalPersonMapper legalPersonMapper;
    private final DegreeMapper degreeMapper;
    private final JobTitleMapper jobTitleMapper;

    @Autowired
    public EnterpriseInformationServiceImpl(EnterpriseInformationMapper enterpriseInformationMapper, LegalPersonMapper legalPersonMapper, DegreeMapper degreeMapper, JobTitleMapper jobTitleMapper) {
        this.enterpriseInformationMapper = enterpriseInformationMapper;
        this.legalPersonMapper = legalPersonMapper;
        this.degreeMapper = degreeMapper;
        this.jobTitleMapper = jobTitleMapper;
    }

    //入住申请提交
    @Override
    public ResultBean<?> updateByPrimaryKey(MultipartFile[] blFile, EnterpriseInformation enterpriseInformation) throws IOException {
        String[] fileNameList = new String[blFile.length];
        String UUName;
        int i = 0;
        for (MultipartFile file : blFile) {
            UUName = FilUploadUtils.saveFile(file);
            fileNameList[i] = UUName;
            i++;
        }
        LegalPerson legalPerson = enterpriseInformation.getLegalPerson();
        legalPerson.setLpCName(enterpriseInformation.getCName());
        legalPersonMapper.insertReturnPrimaryKey(legalPerson);
        String fileName = ArrayUtil.join(fileNameList, "/");//保存文件
        Integer infoid = enterpriseInformation.getInfoid();

        enterpriseInformation.setCId(infoid);
        enterpriseInformation.setCLegalperson(legalPerson.getLpId());//存放法人id
        enterpriseInformation.setFileName(fileName);//获取文件名
        enterpriseInformation.setCStatus(1);//提交企业信息，状态改为1
        return new ResultBean<>(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }

    //企业信息完善（未使用）
    @Override
    public ResultBean<?> updateEnterpriseInformation(EnterpriseInformation enterpriseInformation) throws IOException {
        enterpriseInformation.setCId(enterpriseInformation.getInfoid());
        System.out.println(enterpriseInformation);
        return new ResultBean<>(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }
    //上传主要产品图片
    @Override
    public ResultBean<?> updateByFileProduct(Integer infoid, MultipartFile[] blFile) throws IOException {
        String[] fileNameList = new String[blFile.length];
        String UUName;
        int i = 0;
        for (MultipartFile file : blFile) {
            UUName = FilUploadUtils.saveFile(file);
            fileNameList[i] = UUName;
            i++;
        }
        String fileName = ArrayUtil.join(fileNameList, "/");//保存图片名
        EnterpriseInformation enterpriseInformation=new EnterpriseInformation();
        enterpriseInformation.setCId(infoid);
        enterpriseInformation.setFileName(fileName);//获取文件名
        return new ResultBean<>(enterpriseInformationMapper.updateByFileProduct(enterpriseInformation));
    }

    //企业被拒绝后重新申请
    @Override
    public ResultBean<?> companyAnew(Integer infoid) {
        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(infoid);
        enterpriseInformation.setCStatus(0);
        return new ResultBean<>(enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation));
    }

    @Override
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer info) throws IOException {

        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(info);
        String fileNameString = enterpriseInformation.getFileName();

        if (!StringUtil.empty(fileNameString)) {
            String fileNameArray[] = fileNameString.split("/");
            List<String> fileNameList = new ArrayList<>();
            List<String> filePathList = new ArrayList<>();
            for (int i = 0; i < fileNameArray.length; i++) {

                filePathList.add(FilUploadUtils.getImageShowPath() + fileNameArray[i]);
                fileNameList.add(fileNameArray[i]);//存放带UUID的图片名
                enterpriseInformation.setFilePathName(fileNameList);
                enterpriseInformation.setFilePathList(filePathList);
            }
        }

        if (enterpriseInformation.getCLegalperson() != null) {
            LegalPerson legalPerson = legalPersonMapper.selectByPrimaryKey(enterpriseInformation.getCLegalperson());
            legalPerson.setDegree(degreeMapper.selectByPrimaryKey(legalPerson.getLpDegreeId()));
            legalPerson.setJobTitle(jobTitleMapper.selectByPrimaryKey(legalPerson.getLpJtId()));
            enterpriseInformation.setLegalPerson(legalPerson);
        }
        return new ResultBean<>(enterpriseInformation);
    }

    @Override
    public ResultBean<EnterpriseInformation> getEnterpriseInformationById(Integer cId) {

        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(cId);
        return new ResultBean<>(enterpriseInformation);
    }

    // 获取不带管委会的企业列表
    @Override
    public ResultBean<List<EnterpriseInformation>> getEnterpriseInformationList() {

        List<EnterpriseInformation> enterpriseInformations = enterpriseInformationMapper.selectAllWithoutCommittee();
        return new ResultBean<>(enterpriseInformations);
    }

    // 获取带管委会的企业列表
    @Override
    public ResultBean<List<EnterpriseInformation>> getEnterpriseInformationListWithCommittee() {

        List<EnterpriseInformation> enterpriseInformationList = enterpriseInformationMapper.selectAll();
        return new ResultBean<>(enterpriseInformationList);
    }
}
