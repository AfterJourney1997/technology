package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.*;
import com.technologygarden.entity.*;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DeclareAwardService;
import com.technologygarden.service.EmployeeService;
import com.technologygarden.service.EnterpriseApprovalService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("EnterpriseApprovalService")
public class EnterpriseApprovalServiceImpl implements EnterpriseApprovalService {
    private final RoleMapper roleMapper;
    private final LegalPersonMapper legalPersonMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    private final ChargeMapper chargeMapper;
    private final CooperationMapper cooperationMapper;
    private final DeclareAwardMapper declareAwardMapper;
    private final DeclareAwardService declareAwardService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final OpinionMapper opinionMapper;
    private final PlatformApplicationMapper platformApplicationMapper;
    private final ServiceApplicationMapper serviceApplicationMapper;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public EnterpriseApprovalServiceImpl(RoleMapper roleMapper, LegalPersonMapper legalPersonMapper,
                                         EnterpriseInformationMapper enterpriseInformationMapper,
                                         ChargeMapper chargeMapper, CooperationMapper cooperationMapper,
                                         DeclareAwardMapper declareAwardMapper, DeclareAwardService declareAwardService,
                                         EmployeeMapper employeeMapper, EmployeeService employeeService,
                                         OpinionMapper opinionMapper, PlatformApplicationMapper platformApplicationMapper,
                                         ServiceApplicationMapper serviceApplicationMapper, VehicleMapper vehicleMapper) {
        this.roleMapper = roleMapper;
        this.legalPersonMapper = legalPersonMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
        this.chargeMapper = chargeMapper;
        this.cooperationMapper = cooperationMapper;
        this.declareAwardMapper = declareAwardMapper;
        this.declareAwardService = declareAwardService;
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
        this.opinionMapper = opinionMapper;
        this.platformApplicationMapper = platformApplicationMapper;
        this.serviceApplicationMapper = serviceApplicationMapper;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public ResultBean insertEnterpriseAccount(String account, String enterpriseName) {
        EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
        enterpriseInformation.setCName(enterpriseName);
        enterpriseInformationMapper.insertReturnPrimaryKey(enterpriseInformation);
        Role role = new Role();
        role.setAccount(account);
        role.setRole(2);
        role.setPassword("123456");//设置默认密码
        role.setInfoid(enterpriseInformation.getCId());
        roleMapper.insert(role);
        return new ResultBean();
    }

    @Override
    public ResultBean<PageInfo<?>> getEnterpriseAccount(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<EnterpriseInformation> list = enterpriseInformationMapper.selectAllByPageWithoutCommittee();
        for (EnterpriseInformation enterprise : list) {

            String fileNameString = enterprise.getFileName();
            if (!StringUtil.empty(fileNameString)) {
                String fileNameArray[] = fileNameString.split("/");
                List<String> fileNameList = new ArrayList<>();
                List<String> filePathList = new ArrayList<>();
                for (int i = 0; i < fileNameArray.length; i++) {

                    filePathList.add(FilUploadUtils.getImageShowPath() + fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);//存放带UUID的图片名
                    enterprise.setFilePathName(fileNameList);
                    enterprise.setFilePathList(filePathList);
                }
            }

            Integer legalpersonId = enterprise.getCLegalperson();
            LegalPerson legalPerson = new LegalPerson();
            legalPerson.setLpName("无");
            if (legalpersonId != null) {
                legalPerson = legalPersonMapper.selectByPrimaryKey(legalpersonId);
            }
            String account = roleMapper.selectBycId(enterprise.getCId()).getAccount();
            enterprise.setLegalPerson(legalPerson);
            enterprise.setAccount(account);

        }
        PageInfo<?> pageInfo = new PageInfo<>(list);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean operationEnterpriseAccount(Integer cId, Integer state) {
        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(cId);
        enterpriseInformation.setCStatus(state);
        enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation);
        return new ResultBean(enterpriseInformation);
    }

    @Override
    public ResultBean<PageInfo<?>> getEnterpriseStatistics(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<EnterpriseInformation> list = enterpriseInformationMapper.getEnterpriseStatistics();
        for (EnterpriseInformation enterprise : list) {

            String fileNameString = enterprise.getFileName();
            if (!StringUtil.empty(fileNameString)) {
                String fileNameArray[] = fileNameString.split("/");
                List<String> fileNameList = new ArrayList<>();
                List<String> filePathList = new ArrayList<>();
                for (int i = 0; i < fileNameArray.length; i++) {

                    filePathList.add(FilUploadUtils.getImageShowPath() + fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);//存放带UUID的图片名
                    enterprise.setFilePathName(fileNameList);
                    enterprise.setFilePathList(filePathList);
                }
            }

            Integer legalpersonId = enterprise.getCLegalperson();
            LegalPerson legalPerson = new LegalPerson();
            legalPerson.setLpName("无");
            if (legalpersonId != null) {
                legalPerson = legalPersonMapper.selectByPrimaryKey(legalpersonId);
            }
            String account = roleMapper.selectBycId(enterprise.getCId()).getAccount();
            String password = roleMapper.selectBycId(enterprise.getCId()).getPassword();
            enterprise.setLegalPerson(legalPerson);
            enterprise.setAccount(account);
            enterprise.setPassword(password);

        }
        PageInfo<?> pageInfo = new PageInfo<>(list);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<PageInfo<?>> searchEnterpriseStatistics(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        Page<EnterpriseInformation> list = enterpriseInformationMapper.searchEnterpriseStatistics(search);
        for (EnterpriseInformation enterprise : list) {

            String fileNameString = enterprise.getFileName();
            if (!StringUtil.empty(fileNameString)) {
                String fileNameArray[] = fileNameString.split("/");
                List<String> fileNameList = new ArrayList<>();
                List<String> filePathList = new ArrayList<>();
                for (int i = 0; i < fileNameArray.length; i++) {

                    filePathList.add(FilUploadUtils.getImageShowPath() + fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);//存放带UUID的图片名
                    enterprise.setFilePathName(fileNameList);
                    enterprise.setFilePathList(filePathList);
                }
            }

            Integer legalpersonId = enterprise.getCLegalperson();
            LegalPerson legalPerson = new LegalPerson();
            legalPerson.setLpName("无");
            if (legalpersonId != null) {
                legalPerson = legalPersonMapper.selectByPrimaryKey(legalpersonId);
            }
            String account = roleMapper.selectBycId(enterprise.getCId()).getAccount();
            String password = roleMapper.selectBycId(enterprise.getCId()).getPassword();
            enterprise.setLegalPerson(legalPerson);
            enterprise.setAccount(account);
            enterprise.setPassword(password);

        }
        PageInfo<?> pageInfo = new PageInfo<>(list);
        return new ResultBean<>(pageInfo);
    }

    //删除企业
    @Override
    public ResultBean deleteEnterprise(Integer cId) throws IOException {
        //删除相关收费表
        chargeMapper.deleteChargeBycId(cId);
        //删除校企合作表
        cooperationMapper.deleteCooperationBycId(cId);
        //删除奖项申报表(调用写好的方法，连文件一块删除)
        Page<DeclareAward> declareAwards = declareAwardMapper.getDeclareAwardByPage(cId);
        for (DeclareAward declareAward : declareAwards) {
            declareAwardService.deleteDeclareAward(declareAward.getDId());
        }
        //删除员工表(调用写好的方法，连文件一块删除)
        Page<Employee> employees = employeeMapper.selectByPage(cId);
        for (Employee employee : employees) {
            employeeService.deleteEmployee(employee.getEId());
        }
        //删除意见表
        opinionMapper.deleteBycId(cId);
        //删除平台申请表
        platformApplicationMapper.deleteBycId(cId);
        //删除服务申请表
        serviceApplicationMapper.deleteBycId(cId);
        //删除车辆信息表
        vehicleMapper.deleteBycId(cId);











        //删除企业信息表
        EnterpriseInformation enterpriseInformation=enterpriseInformationMapper.selectByPrimaryKey(cId);
        String fileNameString= enterpriseInformation.getFileName();
        if(!StringUtils.isEmpty(fileNameString)){
            String fileNameArray []=fileNameString.split("/");
            for(int i=0;i<fileNameArray.length;i++) {
                FilUploadUtils.deleteFile(fileNameArray[i]);
            }
        }
        enterpriseInformationMapper.deleteByPrimaryKey(cId);
        //删除用户账号表
        roleMapper.deleteBycId(cId);


        return new ResultBean();
    }
}
