package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServicePolicyRelatedService {

    ResultBean<PageInfo<?>> getPolicyRelatedListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertPolicyRelated(MultipartFile file, PolicyRelated policyRelated);

    ResultBean<?> deletePolicyRelatedById(Integer policyRelatedId);

    ResultBean<?> updatePolicyRelatedById(MultipartFile file, PolicyRelated policyRelated);

    ResultBean<PageInfo<?>> searchPolicyRelatedListByPage(Integer pageNum, Integer pageSize, Integer level, String title);

    ResultBean<List<PolicyRelated>> getAllPolicyRelated();
}
