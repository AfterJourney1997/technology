package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface PoliticsStatusService {
    public ResultBean<PageInfo<?>> getPoliticsStatusByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertPoliticsStatus(PoliticsStatus politicsStatus);
    public ResultBean updatePoliticsStatus(PoliticsStatus politicsStatus);
    public ResultBean deletePoliticsStatus(Integer id);
    public ResultBean<PageInfo<?>> searchPoliticsStatusByName(Integer pageNum,Integer pageSize,String politicsStatusName);
}
