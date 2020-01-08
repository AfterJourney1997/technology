package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.SystemPropertyDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@RequiresPermissions("/system/propertyDevice")
@RequestMapping(value = "/system/propertyDevice")
@Api(tags = "系统配置 / 设备属性管理接口", value = "SystemPropertyDeviceController")
public class SystemPropertyDeviceController {

    private final SystemPropertyDeviceService systemPropertyDeviceService;

    @Autowired
    public SystemPropertyDeviceController(SystemPropertyDeviceService systemPropertyDeviceService) {
        this.systemPropertyDeviceService = systemPropertyDeviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取设备属性列表", notes = "参数包括：页数、每页数量、categoryId，categoryId非必填，如获取设备列表categoryId设0")
    public ResultBean<PageInfo<?>> getSystemPropertyDeviceListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer categoryId) {

        return systemPropertyDeviceService.getSystemPropertyDeviceListByPage(pageNum, pageSize, categoryId);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增设备或属性", notes = "参数包括：设备属性对象（如添加设备，category_id为0，name为设备名；如添加属性，category_id为属性所属的设备id，其他正常填写）")
    public ResultBean<?> insertSystemPropertyDevice(@RequestBody PropertyDevice propertyDevice) {

        return systemPropertyDeviceService.insertSystemPropertyDeviceDynamic(propertyDevice);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除设备或属性", notes = "参数包括：设备或属性id")
    public ResultBean<?> deleteSystemPropertyDeviceById(@NonNull Integer id) {

        return systemPropertyDeviceService.deleteSystemPropertyDeviceById(id);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改设备", notes = "参数包括：设备属性对象")
    public ResultBean<?> updateSystemPropertyDeviceById(@RequestBody PropertyDevice propertyDevice) {

        return systemPropertyDeviceService.updateSystemPropertyDeviceById(propertyDevice);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索设备属性", notes = "参数包括：页数、每页数量、类别id，类别名称、属性名（页数、每页数量必填，其他选填）")
    public ResultBean<PageInfo<?>> searchSystemPropertyDeviceByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer categoryId, String categoryName, String propertyName) {

        return systemPropertyDeviceService.searchSystemPropertyDeviceByPage(pageNum, pageSize, categoryId, categoryName, propertyName);

    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ApiOperation(value = "根据设备类别id获取设备类别信息", notes = "参数包括：设备类别id（不填默认获取全部设备类别）")
    public ResultBean<?> getDeviceCategoryById(Integer propertyDeviceId){

        return  systemPropertyDeviceService.getDeviceCategoryById(propertyDeviceId);

    }
}
