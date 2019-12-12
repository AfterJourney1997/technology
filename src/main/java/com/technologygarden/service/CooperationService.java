package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Cooperation;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ServiceApplication;

import java.util.List;

public interface CooperationService {
    public ResultBean<Page<Cooperation>> getCooperationByPage( Integer pageNum,Integer pageSize, Integer cId);
    public ResultBean insertCooperation(Cooperation cooperation);
    public ResultBean updateCooperation(Cooperation cooperation);
    public ResultBean deleteCooperation(Integer id);
    public ResultBean<Page<Cooperation>> getCooperationByManage( Integer pageNum,Integer pageSize);
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise();
}
