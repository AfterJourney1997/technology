package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface PoliticsStatusService {
    public ResultBean<Page<PoliticsStatus>> getPoliticsStatusByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertPoliticsStatus(PoliticsStatus politicsStatus);
    public ResultBean updatePoliticsStatus(PoliticsStatus politicsStatus);
    public ResultBean deletePoliticsStatus(Integer id);
    public ResultBean<Page<PoliticsStatus>> searchPoliticsStatusByName(Integer pageNum,Integer pageSize,String politicsStatusName);
}
