package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.ServiceApplication;

import java.util.List;

public interface ServiceApplicationService {
    public ResultBean<PageInfo<?>> getServiceApplicationByPage(Integer pageNum, Integer pageSize, Integer cId);
    public ResultBean insertServiceApplication(ServiceApplication serviceApplication);
    public ResultBean updateServiceApplication(ServiceApplication serviceApplication);
    public ResultBean deleteServiceApplication(Integer id);
    public ResultBean<List<Room>> getRoomByInfoid(Integer infoid);
    public ResultBean<PageInfo<?>> getParkServiceByPage( Integer pageNum, Integer pageSize);
    public ResultBean operationParkService(ServiceApplication serviceApplication);
}
