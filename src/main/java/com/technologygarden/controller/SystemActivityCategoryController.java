package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.SystemActivityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@RequiresPermissions("/system/activityCategory")
@RequestMapping(value = "/system")
@Api(tags = "系统配置 / 活动类别管理接口", value = "SystemActivityCategoryController")
public class SystemActivityCategoryController {
    
    private final SystemActivityCategoryService systemActivityCategoryService;

    @Autowired
    public SystemActivityCategoryController(SystemActivityCategoryService systemActivityCategoryService) {
        this.systemActivityCategoryService = systemActivityCategoryService;
    }

    @RequestMapping(value = "/activityCategory", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取活动类别列表", notes = "参数包括：页数、每页数量")
    public ResultBean<Page<ActivityCategory>> getSystemActivityCategoryListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return systemActivityCategoryService.getSystemActivityCategoryListByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/activityCategory", method = RequestMethod.POST)
    @ApiOperation(value = "新增活动类别", notes = "参数包括：活动类别对象")
    public ResultBean<?> insertSystemActivityCategory(@RequestBody ActivityCategory activityCategory) {

        return systemActivityCategoryService.insertSystemActivityCategoryDynamic(activityCategory);

    }

    @RequestMapping(value = "/activityCategory", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除活动类别", notes = "参数包括：活动类别id")
    public ResultBean<?> deleteSystemActivityCategoryById(@NonNull Integer id) {

        return systemActivityCategoryService.deleteSystemActivityCategoryById(id);

    }

    @RequestMapping(value = "/activityCategory", method = RequestMethod.PUT)
    @ApiOperation(value = "修改活动", notes = "参数包括：活动类别对象")
    public ResultBean<?> updateSystemActivityCategoryById(@RequestBody ActivityCategory activityCategory) {

        return systemActivityCategoryService.updateSystemActivityCategoryById(activityCategory);

    }

    @RequestMapping(value = "/activityCategory/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索活动类别", notes = "参数包括：页数、每页数量、活动类别名")
    public ResultBean<Page<ActivityCategory>> searchSystemActivityCategoryByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String category) {

        return systemActivityCategoryService.searchSystemActivityCategoryByPage(pageNum, pageSize, category);

    }
    
}
