package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.AssetDeviceService;
import com.technologygarden.service.RoomCompanyService;
import com.technologygarden.service.SystemPropertyDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequiresPermissions("/asset/furniture")
@RequestMapping(value = "/asset/furniture")
@Api(tags = "资产管理 / 家具接口", value = "AssetFurnitureController")
public class AssetFurnitureController {

    private final AssetDeviceService assetDeviceService;
    private final SystemPropertyDeviceService systemPropertyDeviceService;
    private final RoomCompanyService roomCompanyService;

    @Autowired
    public AssetFurnitureController(AssetDeviceService assetDeviceService, SystemPropertyDeviceService systemPropertyDeviceService, RoomCompanyService roomCompanyService) {
        this.assetDeviceService = assetDeviceService;
        this.systemPropertyDeviceService = systemPropertyDeviceService;
        this.roomCompanyService = roomCompanyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取家具列表", notes = "参数包括：页数，每页数量，均必填（owner为1表示学校建设，2表示企业自建，kind为1表示设备，2表示家具）")
    public ResultBean<PageInfo<?>> getFurnitureListWithPropertyByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getFurnitureListWithPropertyByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增家具及其属性", notes = "参数包括：家具对象，device中deviceId不填、remain与total相等、kind固定为2；propertyDeviceList里填写属性，较复杂使用找我口述")
    public ResultBean<?> insertFurniture(@RequestBody Device furniture){

        return assetDeviceService.insertFurnitureWithPropertyDynamic(furniture);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除家具及其属性", notes = "参数包括：家具主键id")
    public ResultBean<?> deleteFurnitureById(@NonNull Integer furnitureId){

        return assetDeviceService.deleteDeviceById(furnitureId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改家具信息及其属性", notes = "参数包括：家具对象，对象id必填，不修改的项填空（不支持修改家具kind和家具类别，属性只支持修改value）")
    public ResultBean<?> updateFurnitureById(@RequestBody Device furniture){

        return assetDeviceService.updateDeviceById(furniture);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索家具列表及其属性", notes = "参数包括：页数，每页数量，类别id，家具名称，所属单位")
    public ResultBean<PageInfo<?>> searchFurnitureListWithPropertyByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer categoryId, String furnitureName, Integer owner){

        return assetDeviceService.searchFurnitureListWithPropertyByPage(pageNum, pageSize, categoryId, furnitureName, owner);

    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ApiOperation(value = "获取家具类别信息", notes = "参数包括：无")
    public ResultBean<List<PropertyDevice>> getFurniturePropertyDevice(){

        return systemPropertyDeviceService.getFurniturePropertyDevice();

    }

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    @ApiOperation(value = "根据类别id获取相关属性", notes = "参数包括：类别id")
    public ResultBean<List<PropertyDevice>> getPropertyByCategoryId(@NonNull Integer categoryId){

        return systemPropertyDeviceService.getPropertyByCategoryId(categoryId);

    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部有企业入驻的房间列表", notes = "参数包括：无")
    public ResultBean<List<Room>> getRoomEntered() {

        return roomCompanyService.getRoomEntered();

    }

    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    @ApiOperation(value = "分配家具到企业入驻的房间", notes = "参数包括：设备id、设备分配数量、企业id、房间id")
    public ResultBean<?> distributeDevice(@NonNull Integer deviceId, @NonNull Integer deviceNum, @NonNull Integer companyId, @NonNull Integer roomId) {

        return assetDeviceService.distributeDevice(deviceId, deviceNum, companyId, roomId);

    }


}
