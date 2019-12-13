package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.LegalPersonMapper;
import com.technologygarden.dao.RoleMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EnterpriseApprovalService;
import org.springframework.stereotype.Service;

@Service("EnterpriseApprovalService")
public class EnterpriseApprovalServiceImpl implements EnterpriseApprovalService {
    private final RoleMapper roleMapper;
    private final LegalPersonMapper legalPersonMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;

    public EnterpriseApprovalServiceImpl(RoleMapper roleMapper, LegalPersonMapper legalPersonMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.roleMapper = roleMapper;
        this.legalPersonMapper = legalPersonMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean insertEnterpriseAccount(String account, String enterpriseName) {
        EnterpriseInformation enterpriseInformation=new EnterpriseInformation();
        enterpriseInformation.setCName(enterpriseName);
        enterpriseInformationMapper.insertReturnPrimaryKey(enterpriseInformation);
        Role role=new Role();
        role.setAccount(account);
        role.setRole(2);
        role.setPassword("123456");
        role.setInfoid(enterpriseInformation.getCId());
        roleMapper.insert(role);
        return new ResultBean();
    }

    @Override
    public ResultBean<Page<EnterpriseInformation>> getEnterpriseAccount(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<EnterpriseInformation> list = enterpriseInformationMapper.selectAllByPage();
        for(EnterpriseInformation enterprise:list){
            Integer legalpersonId= enterprise.getCLegalperson();
            LegalPerson legalPerson =new LegalPerson();
            legalPerson.setLpCName("无");
            if(legalpersonId!=null){
              legalPerson =legalPersonMapper.selectByPrimaryKey(legalpersonId);
            }
            String account=roleMapper.selectBycId(enterprise.getCId()).getAccount();
            enterprise.setLegalPerson(legalPerson);
            enterprise.setAccount(account);
        }
        return new ResultBean<>(list);
    }

    @Override
    public ResultBean operationEnterpriseAccount(Integer cId, Integer state) {
        EnterpriseInformation enterpriseInformation=enterpriseInformationMapper.selectByPrimaryKey(cId);
        enterpriseInformation.setCStatus(state);
        enterpriseInformationMapper.updateByPrimaryKey(enterpriseInformation);
        return new ResultBean(enterpriseInformation);
    }
}
