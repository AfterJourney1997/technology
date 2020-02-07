package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.io.IOException;

public interface EnterpriseApprovalService {

    public ResultBean<?> insertEnterpriseAccount(String account, String enterpriseName);

    public ResultBean<PageInfo<?>> getEnterpriseAccount(Integer pageNum, Integer pageSize);

    public ResultBean<?> operationEnterpriseAccount(Integer cId, Integer state,String comment);

    public ResultBean<PageInfo<?>> getEnterpriseStatistics(Integer pageNum,Integer pageSize);

    public ResultBean<PageInfo<?>> searchEnterpriseStatistics( Integer pageNum, Integer pageSize, String search);

    public ResultBean<?> deleteEnterprise(Integer cId) throws IOException;

    public ResultBean<?> getNoApprovalCompanyNum();
}
