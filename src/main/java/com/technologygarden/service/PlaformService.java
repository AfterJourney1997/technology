package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;


public interface PlaformService {
    public ResultBean<Page<PlatformApplication>> getPlatformApplicationByPage(Integer pageNum, Integer pageSize,Integer cId);
    public ResultBean insertPlatformApplication(PlatformApplication platformApplication);
    public ResultBean updatePlatformApplication(PlatformApplication platformApplication);
    public ResultBean deletePlatformApplication(Integer pId);
    public ResultBean<Page<PlatformApplication>> selectAll(Integer pageNum, Integer pageSize);
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise();
}
