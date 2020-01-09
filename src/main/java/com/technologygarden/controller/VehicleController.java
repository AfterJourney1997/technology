package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.Vehicle;
import com.technologygarden.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage")
@Api(tags = "园区管理/车辆管理接口", value = "VehicleController")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
    @ApiOperation(value = "获取车辆信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> selectVehicleByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return vehicleService.selectVehicleByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.POST)
    @ApiOperation(value = "新增车辆信息", notes = "参数包括：车辆信息Vehicle对象，企业和房间传对应的id")
    public ResultBean insertVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.insertVehicle(vehicle);
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.PUT)
    @ApiOperation(value = "修改车辆信息", notes = "参数包括：修改后的车辆信息Vehicle对象，企业和房间传对应的id")
    public ResultBean updateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除车辆信息", notes = "参数包括：待删除车辆信息Vehicle对象的id")
    public ResultBean deleteVehicle(Integer id) {
        return vehicleService.deleteVehicle(id);
    }

    @RequestMapping(value = "/vehicle/company", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业列表", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getCompanyAll() {
        return vehicleService.getCompanyAll();
    }

    @RequestMapping(value = "/vehicle/room", method = RequestMethod.GET)
    @ApiOperation(value = "根据企业cId获取企业房间", notes = "参数包括：企业cId")
    public ResultBean<List<Room>> getRoomBycId(Integer cId) {
        return vehicleService.getRoomBycId(cId);
    }

    @RequestMapping(value = "/vehicle/search", method = RequestMethod.GET)
    @ApiOperation(value = "按车牌号搜索", notes = "参数包括：页数,每页数量,搜索内容")
    public ResultBean<PageInfo<?>> searchVehicleByNumberPlate(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String search) {
        return vehicleService.searchVehicleByNumberPlate(pageNum, pageSize, search);
    }
}
