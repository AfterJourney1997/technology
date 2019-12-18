package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ServicePolicyRelatedService {

    ResultBean<PageInfo<?>> getPolicyRelatedListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertPolicyRelated(PolicyRelated policyRelated);

    ResultBean<?> deletePolicyRelatedById(Integer policyRelatedId);

    ResultBean<?> updatePolicyRelatedById(PolicyRelated policyRelated);

    ResultBean<PageInfo<?>> searchPolicyRelatedListByPage(Integer pageNum, Integer pageSize, Integer level, String title);
}
