package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.SystemActivityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@RequiresPermissions("/system/activityCategory")
@RequestMapping(value = "/system/activityCategory")
@Api(tags = "系统配置 / 活动类别管理接口", value = "SystemActivityCategoryController")
public class SystemActivityCategoryController {
    
    private final SystemActivityCategoryService systemActivityCategoryService;

    @Autowired
    public SystemActivityCategoryController(SystemActivityCategoryService systemActivityCategoryService) {
        this.systemActivityCategoryService = systemActivityCategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取活动类别列表", notes = "参数包括：页数、每页数量")
    public ResultBean<PageInfo<?>> getSystemActivityCategoryListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return systemActivityCategoryService.getSystemActivityCategoryListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增活动类别", notes = "参数包括：活动类别对象")
    public ResultBean<?> insertSystemActivityCategory(@RequestBody ActivityCategory activityCategory) {

        return systemActivityCategoryService.insertSystemActivityCategoryDynamic(activityCategory);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动类别", notes = "参数包括：活动类别id")
    public ResultBean<?> deleteSystemActivityCategoryById(@NonNull Integer id) {

        return systemActivityCategoryService.deleteSystemActivityCategoryById(id);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改活动", notes = "参数包括：活动类别对象")
    public ResultBean<?> updateSystemActivityCategoryById(@RequestBody ActivityCategory activityCategory) {

        return systemActivityCategoryService.updateSystemActivityCategoryById(activityCategory);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索活动类别", notes = "参数包括：页数、每页数量、活动类别名")
    public ResultBean<PageInfo<?>> searchSystemActivityCategoryByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String category) {

        return systemActivityCategoryService.searchSystemActivityCategoryByPage(pageNum, pageSize, category);

    }
    
}
