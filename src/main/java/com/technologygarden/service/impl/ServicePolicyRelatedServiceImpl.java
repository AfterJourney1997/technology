package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.PolicyRelatedMapper;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServicePolicyRelatedService;
import com.technologygarden.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servicePolicyRelatedService")
public class ServicePolicyRelatedServiceImpl implements ServicePolicyRelatedService {

    private final PolicyRelatedMapper policyRelatedMapper;

    @Autowired
    public ServicePolicyRelatedServiceImpl(PolicyRelatedMapper policyRelatedMapper) {
        this.policyRelatedMapper = policyRelatedMapper;
    }

    @Override
    public ResultBean<Page<PolicyRelated>> getPolicyRelatedListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PolicyRelated> policyRelatedList = policyRelatedMapper.getPolicyRelatedListByPage();
        return new ResultBean<>(policyRelatedList);

    }

    @Override
    public ResultBean<?> insertPolicyRelated(PolicyRelated policyRelated) {

        // 文件存储

        policyRelatedMapper.insert(policyRelated);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deletePolicyRelatedById(Integer policyRelatedId) {

        // 文件删除

        policyRelatedMapper.deleteByPrimaryKey(policyRelatedId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updatePolicyRelatedById(PolicyRelated policyRelated) {

        // 判断是否有参数缺失
        if(policyRelated.getPrId() == null){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }

        policyRelatedMapper.updateByIdDynamic(policyRelated);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<PolicyRelated>> searchPolicyRelatedListByPage(Integer pageNum, Integer pageSize, Integer level, String title) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PolicyRelated> policyRelatedList = policyRelatedMapper.searchPolicyRelatedListByPage(level, title);
        return new ResultBean<>(policyRelatedList);

    }
}
