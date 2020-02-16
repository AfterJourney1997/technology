package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.*;
import com.technologygarden.entity.*;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountService;
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
import java.util.Date;
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
    private final RoomMapper roomMapper;
    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;
    private final AssetAssetCountService assetAssetCountService;
    private final PowerLoadMapper powerLoadMapper;
    private final ApprovedMemoMapper approvedMemoMapper;

    @Autowired
    public EnterpriseApprovalServiceImpl(RoleMapper roleMapper, LegalPersonMapper legalPersonMapper,
                                         EnterpriseInformationMapper enterpriseInformationMapper,
                                         ChargeMapper chargeMapper, CooperationMapper cooperationMapper,
                                         DeclareAwardMapper declareAwardMapper, DeclareAwardService declareAwardService,
                                         EmployeeMapper employeeMapper, EmployeeService employeeService,
                                         OpinionMapper opinionMapper, PlatformApplicationMapper platformApplicationMapper,
                                         ServiceApplicationMapper serviceApplicationMapper, VehicleMapper vehicleMapper, RoomMapper roomMapper, CompanyRoomDeviceMapper companyRoomDeviceMapper, AssetAssetCountService assetAssetCountService, PowerLoadMapper powerLoadMapper, ApprovedMemoMapper approvedMemoMapper) {
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
        this.roomMapper = roomMapper;
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
        this.assetAssetCountService = assetAssetCountService;
        this.powerLoadMapper = powerLoadMapper;
        this.approvedMemoMapper = approvedMemoMapper;
    }

    @Override
    public ResultBean<?> insertEnterpriseAccount(String account, String enterpriseName, String phone) {

        // 默认的企业密码
        String password = "123456";

        // 首先插入企业信息
        EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
        enterpriseInformation.setCName(enterpriseName);
        enterpriseInformationMapper.insertReturnPrimaryKey(enterpriseInformation);

        // 再插入企业的账号信息
        Role role = Role.builder().account(account)
                                    .role(2)    // role为2，该账户为企业
                                    .password(password) // 密码为默认
                                    .phone(phone)
                                    .infoid(enterpriseInformation.getCId())
                                    .build();
        roleMapper.insert(role);

        return new ResultBean<>();
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
                for (String s : fileNameArray) {

                    filePathList.add(FilUploadUtils.getImageShowPath() + s);
                    fileNameList.add(s);//存放带UUID的图片名
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
    public ResultBean<?> operationEnterpriseAccount(Integer cId, Integer state, String comment,String approver) {
        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(cId);
        enterpriseInformation.setCStatus(state);
        enterpriseInformation.setComment(comment);
        enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation);
        //创建审核记录对象，记录当前审批
        ApprovedMemo approvedMemo = new ApprovedMemo();
        approvedMemo.setCId(enterpriseInformation.getCId());
        approvedMemo.setCName(enterpriseInformation.getCName());
        approvedMemo.setResult(state);
        approvedMemo.setComment(comment);
        approvedMemo.setDate(new Date());
        approvedMemo.setApprover(approver);
        approvedMemoMapper.insert(approvedMemo);
        return new ResultBean<>(enterpriseInformation);
    }

    @Override
    public ResultBean<PageInfo<?>> getEnterpriseStatistics(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<EnterpriseInformation> list = enterpriseInformationMapper.getEnterpriseStatistics();
        for (EnterpriseInformation enterprise : list) {

            String fileNameString = enterprise.getFileName();
            if (!StringUtil.empty(fileNameString)) {
                String[] fileNameArray = fileNameString.split("/");
                List<String> fileNameList = new ArrayList<>();
                List<String> filePathList = new ArrayList<>();
                for (String s : fileNameArray) {

                    filePathList.add(FilUploadUtils.getImageShowPath() + s);
                    fileNameList.add(s);//存放带UUID的图片名
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
                String[] fileNameArray = fileNameString.split("/");
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
    public ResultBean<?> deleteEnterprise(Integer cId) throws IOException {

        EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(cId);

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

        // 清空该企业的入驻房间信息，重置房间状态
        roomMapper.emptyCompanyInfoByCId(cId);

        // 回收分配到该企业资产
        List<CompanyRoomDevice> companyRoomDeviceList = companyRoomDeviceMapper.selectByCompanyId(cId);
        for (CompanyRoomDevice companyRoomDevice : companyRoomDeviceList) {
            assetAssetCountService.deleteAssetCount(companyRoomDevice);
        }

        // 删除用电负荷表中相关信息
        powerLoadMapper.deletePowerLoadByCompanyId(cId);

        // 删除企业法人
        legalPersonMapper.deleteByPrimaryKey(enterpriseInformation.getCLegalperson());


        //删除企业信息表（删除企业附件和产品文件）
        String fileNameString = enterpriseInformation.getFileName();
        String fileProduct = enterpriseInformation.getFileProduct();
        if (!StringUtils.isEmpty(fileNameString)) {
            String[] fileNameArray = fileNameString.split("/");
            for (String s : fileNameArray) {
                FilUploadUtils.deleteFile(s);
            }
        }
        if (!StringUtils.isEmpty(fileProduct)) {
            String[] fileNameArray = fileNameString.split("/");
            for (String s : fileNameArray) {
                FilUploadUtils.deleteFile(s);
            }
        }
        enterpriseInformationMapper.deleteByPrimaryKey(cId);
        //删除用户账号表
        roleMapper.deleteBycId(cId);


        return new ResultBean<>();
    }

    @Override
    public ResultBean<String> getNoApprovalCompanyNum() {
        return new ResultBean<>(Integer.toString(enterpriseInformationMapper.getNoApprovalCompanyNum()));
    }
}
