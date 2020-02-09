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

import java.util.List;

@CrossOrigin
@RestController
//@RequiresPermissions("/system/propertyDevice")
@RequestMapping(value = "/system/propertyDevice")
@Api(tags = "系统配置 / 资源属性管理接口", value = "SystemPropertyDeviceController")
public class SystemPropertyDeviceController {

    private final SystemPropertyDeviceService systemPropertyDeviceService;

    @Autowired
    public SystemPropertyDeviceController(SystemPropertyDeviceService systemPropertyDeviceService) {
        this.systemPropertyDeviceService = systemPropertyDeviceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取资源列表", notes = "参数包括：页数、每页数量、kind，kind为空获取全部，1获取家具，2获取设备")
    public ResultBean<PageInfo<?>> getFatherSystemPropertyDeviceListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer kind) {

        return systemPropertyDeviceService.getFatherSystemPropertyDeviceListByPage(pageNum, pageSize, kind);

    }

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    @ApiOperation(value = "根据资源id获取对应属性", notes = "参数包括：categoryId（即资源主键id）")
    public ResultBean<List<PropertyDevice>> getPropertyByCategoryId(@NonNull Integer categoryId){

        return systemPropertyDeviceService.getPropertyByCategoryId(categoryId);

    }


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增资源或属性", notes = "参数包括：设备属性对象（如添加设备，category_id为0，name为设备名；如添加属性，category_id为属性所属的设备id，其他正常填写）")
    public ResultBean<?> insertSystemPropertyDevice(@RequestBody PropertyDevice propertyDevice) {

        return systemPropertyDeviceService.insertSystemPropertyDeviceDynamic(propertyDevice);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除资源或属性", notes = "参数包括：设备或属性id")
    public ResultBean<?> deleteSystemPropertyDeviceById(@NonNull Integer id) {

        return systemPropertyDeviceService.deleteSystemPropertyDeviceById(id);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改资源", notes = "参数包括：设备属性对象")
    public ResultBean<?> updateSystemPropertyDeviceById(@RequestBody PropertyDevice propertyDevice) {

        return systemPropertyDeviceService.updateSystemPropertyDeviceById(propertyDevice);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索资源", notes = "参数包括：页数、每页数量、资源名称（页数、每页数量必填，其他选填）")
    public ResultBean<PageInfo<?>> searchFatherSystemPropertyDeviceByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String categoryName) {

        return systemPropertyDeviceService.searchFatherSystemPropertyDeviceByPage(pageNum, pageSize, categoryName);

    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ApiOperation(value = "根据设备类别id获取设备类别信息", notes = "参数包括：设备类别id（不填默认获取全部设备类别）")
    public ResultBean<?> getDeviceCategoryById(Integer propertyDeviceId){

        return  systemPropertyDeviceService.getDeviceCategoryById(propertyDeviceId);

    }
}
