package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.Vehicle;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VehicleService {
    public ResultBean<PageInfo<?>> selectVehicleByPage( Integer pageNum, Integer pageSize);
    public ResultBean<List<EnterpriseInformation>> getCompanyAll();
    public ResultBean<List<Room>> getRoomBycId(Integer cId);
    public ResultBean insertVehicle(Vehicle vehicle);
    public ResultBean updateVehicle(Vehicle vehicle);
    public ResultBean deleteVehicle(Integer id);
    public ResultBean<PageInfo<?>> searchVehicleByNumberPlate(Integer pageNum,Integer pageSize,String search);
}
