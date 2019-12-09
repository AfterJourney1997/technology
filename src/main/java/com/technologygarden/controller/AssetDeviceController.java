package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/asset")
@Api(tags = "资产管理 / 设备接口", value = "AssetDeviceController")
public class AssetDeviceController {

    private final AssetDeviceService assetDeviceService;

    @Autowired
    public AssetDeviceController(AssetDeviceService assetDeviceService) {
        this.assetDeviceService = assetDeviceService;
    }

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备列表", notes = "参数包括：页数，每页数量，均必填（owner为1表示学校建设，2表示企业自建，kind为1表示设备，2表示家具）")
    public ResultBean<Page<Device>> getDeviceListWithPropertyByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getDeviceListWithPropertyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ApiOperation(value = "新增设备及其属性", notes = "参数包括：设备对象，device中deviceId不填、remain与total相等、kind固定为1；propertyDeviceList里填写属性，较复杂使用找我口述")
    public ResultBean<?> insertDevice(@RequestBody Device device){

        return assetDeviceService.insertDeviceWithPropertyDynamic(device);

    }

    @RequestMapping(value = "/device", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除设备及其属性", notes = "参数包括：设备主键id")
    public ResultBean<?> deleteDeviceById(@NonNull Integer deviceId){

        return assetDeviceService.deleteDeviceById(deviceId);

    }

    @RequestMapping(value = "/device", method = RequestMethod.PUT)
    @ApiOperation(value = "修改设备信息及其属性", notes = "参数包括：设备对象，对象id必填，不修改的项填空（不支持修改设备kind和设备类别，属性只支持修改value）")
    public ResultBean<?> updateDeviceById(@RequestBody Device device){

        return assetDeviceService.updateDeviceById(device);

    }

    @RequestMapping(value = "/device/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索设备列表及其属性", notes = "参数包括：页数，每页数量，类别id，设备名称，所属单位")
    public ResultBean<Page<Device>> searchDeviceListWithPropertyByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer categoryId, String deviceName, Integer owner){

        return assetDeviceService.searchDeviceListWithPropertyByPage(pageNum, pageSize, categoryId, deviceName, owner);

    }

}
