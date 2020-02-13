package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.io.IOException;

public interface EnterpriseApprovalService {

    ResultBean<?> insertEnterpriseAccount(String account, String enterpriseName, String phone);

    ResultBean<PageInfo<?>> getEnterpriseAccount(Integer pageNum, Integer pageSize);

    ResultBean<?> operationEnterpriseAccount(Integer cId, Integer state, String comment,String approver);

    ResultBean<PageInfo<?>> getEnterpriseStatistics(Integer pageNum, Integer pageSize);

    ResultBean<PageInfo<?>> searchEnterpriseStatistics(Integer pageNum, Integer pageSize, String search);

    ResultBean<?> deleteEnterprise(Integer cId) throws IOException;

    ResultBean<String> getNoApprovalCompanyNum();
}
