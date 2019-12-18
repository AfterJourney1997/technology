package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.MaintainMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.dao.ServiceApplicationMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.ServiceApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service("ServiceApplicationService")
public class ServiceApplicationServiceImpl implements ServiceApplicationService {
    private final ServiceApplicationMapper serviceApplicationMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    private final MaintainMapper maintainMapper;
    private final RoomMapper roomMapper;
    @Autowired
    public ServiceApplicationServiceImpl(ServiceApplicationMapper serviceApplicationMapper, EnterpriseInformationMapper enterpriseInformationMapper, MaintainMapper maintainMapper, RoomMapper roomMapper) {
        this.serviceApplicationMapper = serviceApplicationMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
        this.maintainMapper = maintainMapper;
        this.roomMapper = roomMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getServiceApplicationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ServiceApplication> serviceApplications=serviceApplicationMapper.getServiceApplicationByPage(cId);
        for(ServiceApplication serviceApplication:serviceApplications){
            if(serviceApplication.getMaintainId()!=null){
                log.info("serviceApplication.getMaintainId():"+serviceApplication.getMaintainId());
                serviceApplication.setServicename(maintainMapper.selectByPrimaryKey(serviceApplication.getMaintainId()).getServicename());
            }
            if(serviceApplication.getRoomId()!=null){
                log.info("serviceApplication.getRoomId():"+serviceApplication.getRoomId());
                serviceApplication.setRoomName(roomMapper.selectByPrimaryKey(serviceApplication.getRoomId()).getRName());
            }
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
    //园区服务

    @Override
    public ResultBean<PageInfo<?>> getParkServiceByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<ServiceApplication> serviceApplications=serviceApplicationMapper.getAllServiceApplication();
        for(ServiceApplication serviceApplication:serviceApplications){
            serviceApplication.setCName(enterpriseInformationMapper.selectByPrimaryKey(serviceApplication.getCId()).getCName());
            if(serviceApplication.getMaintainId()!=null){
                log.info("serviceApplication.getMaintainId():"+serviceApplication.getMaintainId());
                serviceApplication.setServicename(maintainMapper.selectByPrimaryKey(serviceApplication.getMaintainId()).getServicename());
            }
            if(serviceApplication.getRoomId()!=null){
                log.info("serviceApplication.getRoomId():"+serviceApplication.getRoomId());
                serviceApplication.setRoomName(roomMapper.selectByPrimaryKey(serviceApplication.getRoomId()).getRName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(serviceApplications);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean operationParkService(ServiceApplication serviceApplication) {
        log.info("operationParkService:"+serviceApplication);
        return new ResultBean(serviceApplicationMapper.updateByPrimaryKey(serviceApplication));
    }
}
