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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

/*    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ApiOperation(value = "新增设备及其属性", notes = "参数包括：设备对象， ")
    public ResultBean<?> insertDevice(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getDeviceListWithPropertyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/device", method = RequestMethod.DELETE)
    @ApiOperation(value = "分页获取设备列表", notes = "参数包括：页数，每页数量，均必填（owner为1表示学校建设，2表示企业自建，kind为1表示设备，2表示家具）")
    public ResultBean deleteDeviceById(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getDeviceListWithPropertyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/device", method = RequestMethod.PUT)
    @ApiOperation(value = "分页获取设备列表", notes = "参数包括：页数，每页数量，均必填（owner为1表示学校建设，2表示企业自建，kind为1表示设备，2表示家具）")
    public ResultBean updateDeviceById(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getDeviceListWithPropertyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/device/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备列表", notes = "参数包括：页数，每页数量，均必填（owner为1表示学校建设，2表示企业自建，kind为1表示设备，2表示家具）")
    public ResultBean<Page<Device>> searchDeviceListWithPropertyByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetDeviceService.getDeviceListWithPropertyByPage(pageNum, pageSize);

    }*/

}
