package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.MaintainMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.dao.ServiceApplicationMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.ServiceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ServiceApplicationService")
public class ServiceApplicationServiceImpl implements ServiceApplicationService {
    private final ServiceApplicationMapper serviceApplicationMapper;
    private final MaintainMapper maintainMapper;
    private final RoomMapper roomMapper;
    @Autowired
    public ServiceApplicationServiceImpl(ServiceApplicationMapper serviceApplicationMapper, MaintainMapper maintainMapper, RoomMapper roomMapper) {
        this.serviceApplicationMapper = serviceApplicationMapper;
        this.maintainMapper = maintainMapper;
        this.roomMapper = roomMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getServiceApplicationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ServiceApplication> serviceApplications=serviceApplicationMapper.getServiceApplicationByPage(cId);
        for(ServiceApplication serviceApplication:serviceApplications){
            serviceApplication.setServicename(maintainMapper.selectByPrimaryKey(serviceApplication.getMaintainId()).getServicename());
        }
        PageInfo<?> pageInfo = new PageInfo<>(serviceApplications);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertServiceApplication(ServiceApplication serviceApplication) {
        serviceApplication.setDate(new Date());
        return new ResultBean(serviceApplicationMapper.insert(serviceApplication)) ;
    }

    @Override
    public ResultBean updateServiceApplication(ServiceApplication serviceApplication) {
        serviceApplication.setDate(new Date());
        return new ResultBean(serviceApplicationMapper.updateByPrimaryKey(serviceApplication));
    }

    @Override
    public ResultBean deleteServiceApplication(Integer id) {
        return new ResultBean(serviceApplicationMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<List<Room>> getRoomByInfoid(Integer infoid) {
        List<Room> roomList = roomMapper.selectRoomByCompanyId(infoid);
        return new ResultBean<>(roomList);
    }
}
