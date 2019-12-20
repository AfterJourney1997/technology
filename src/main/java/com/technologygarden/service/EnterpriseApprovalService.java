package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface EnterpriseApprovalService {
    public ResultBean insertEnterpriseAccount(String account, String enterpriseName);

    public ResultBean<PageInfo<?>> getEnterpriseAccount(Integer pageNum, Integer pageSize);

    public ResultBean operationEnterpriseAccount(Integer cId, Integer state);
}
