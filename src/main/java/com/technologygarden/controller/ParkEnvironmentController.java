package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ParkEnvironment;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ParkEnvironmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage/park")
@Api(tags = "园区管理/园区环境接口", value = "ParkEnvironmentController")
public class ParkEnvironmentController {

    private final ParkEnvironmentService parkEnvironmentService;

    @Autowired
    public ParkEnvironmentController(ParkEnvironmentService parkEnvironmentService) {
        this.parkEnvironmentService = parkEnvironmentService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页园区环境列表", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> getParkEnvironmentByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return parkEnvironmentService.getParkEnvironmentByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/building", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的房区列表", notes = "参数包括：无")
    public ResultBean<List<Building>> getBuildingAll() {
        return parkEnvironmentService.getAllBuilding();
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增园区环境列表", notes = "参数包括：ParkEnvironment对象，房区传buildingId")
    public ResultBean insertParkEnvironment(@RequestBody ParkEnvironment parkEnvironment) {
        return parkEnvironmentService.insertParkEnvironment(parkEnvironment);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改园区环境对象", notes = "参数包括：修改后的ParkEnvironment对象，房区传buildingId")
    public ResultBean updateParkEnvironment(@RequestBody ParkEnvironment parkEnvironment) {
        return parkEnvironmentService.updateParkEnvironment(parkEnvironment);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除园区环境对象", notes = "参数包括：ParkEnvironment对象的id")
    public ResultBean deleteParkEnvironment(Integer id) {
        return parkEnvironmentService.deleteParkEnvironment(id);
    }
}
