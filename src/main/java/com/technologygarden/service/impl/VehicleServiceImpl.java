package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.dao.VehicleMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.Vehicle;
import com.technologygarden.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("VehicleService")
public class VehicleServiceImpl implements VehicleService {
    private final VehicleMapper vehicleMapper;
    private final RoomMapper roomMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    @Autowired
    public VehicleServiceImpl(VehicleMapper vehicleMapper, RoomMapper roomMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.vehicleMapper = vehicleMapper;
        this.roomMapper = roomMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> selectVehicleByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Vehicle> vehicles=vehicleMapper.selectVehicleByPage();
        for (Vehicle vehicle:vehicles){
            if(vehicle.getCid()!=null){
                vehicle.setCName(enterpriseInformationMapper.selectByPrimaryKey(vehicle.getCid()).getCName());
            }
            if(vehicle.getRoomid()!=null){
                vehicle.setRoomName(roomMapper.selectByPrimaryKey(vehicle.getRoomid()).getRName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(vehicles);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getCompanyAll() {
        return new ResultBean<>(enterpriseInformationMapper.selectAll());
    }

    @Override
    public ResultBean<List<Room>> getRoomBycId(Integer cId) {
        return new ResultBean<>(roomMapper.selectRoomByCompanyId(cId));
    }

    @Override
    public ResultBean insertVehicle(Vehicle vehicle) {
        return new ResultBean(vehicleMapper.insert(vehicle));
    }

    @Override
    public ResultBean updateVehicle(Vehicle vehicle) {
        return new ResultBean(vehicleMapper.updateByPrimaryKey(vehicle));
    }

    @Override
    public ResultBean deleteVehicle(Integer id) {
        return new ResultBean(vehicleMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<PageInfo<?>> searchVehicleByNumberPlate(Integer pageNum,Integer pageSize,String search) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Vehicle> vehicles=vehicleMapper.searchVehicleByNumberPlate(search);
        for (Vehicle vehicle:vehicles){
            if(vehicle.getCid()!=null){
                vehicle.setCName(enterpriseInformationMapper.selectByPrimaryKey(vehicle.getCid()).getCName());
            }
            if(vehicle.getRoomid()!=null){
                vehicle.setRoomName(roomMapper.selectByPrimaryKey(vehicle.getRoomid()).getRName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(vehicles);
        return new ResultBean<>(pageInfo);
    }
}
