package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;


public interface PlaformService {
    public ResultBean<Page<PlatformApplication>> getPlatformApplicationByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertPlatformApplication(PlatformApplication platformApplication);
    public ResultBean updatePlatformApplication(PlatformApplication platformApplication);
    public ResultBean deletePlatformApplication(Integer pId);
}
