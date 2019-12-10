package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ServiceApplication;

public interface ServiceApplicationService {
    public ResultBean<Page<ServiceApplication>> getServiceApplicationByPage(Integer pageNum, Integer pageSize, Integer cId);
    public ResultBean insertServiceApplication(ServiceApplication serviceApplication);
    public ResultBean updateServiceApplication(ServiceApplication serviceApplication);
    public ResultBean deleteServiceApplication(Integer id);
}
