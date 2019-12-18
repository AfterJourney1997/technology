package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceActivityIncubationService;
import com.technologygarden.service.SystemActivityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
//@RequiresPermissions("/service/activityIncubation")
@RequestMapping(value = "/service")
@Api(tags = "孵化服务 / 孵化活动接口", value = "ServiceActivityIncubationController")
public class ServiceActivityIncubationController {

    private final ServiceActivityIncubationService serviceActivityIncubationService;
    private final SystemActivityCategoryService systemActivityCategoryService;

    @Autowired
    public ServiceActivityIncubationController(ServiceActivityIncubationService serviceActivityIncubationService, SystemActivityCategoryService systemActivityCategoryService) {
        this.serviceActivityIncubationService = serviceActivityIncubationService;
        this.systemActivityCategoryService = systemActivityCategoryService;
    }

    @RequestMapping(value = "/activityIncubation", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取孵化活动列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getActivityIncubationListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return serviceActivityIncubationService.getActivityIncubationListByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/activityIncubation", method = RequestMethod.POST)
    @ApiOperation(value = "新增孵化活动", notes = "参数包括：孵化活动对象")
    public ResultBean<?> insertActivityIncubation(@Valid ActivityIncubation activityIncubation, BindingResult errors){

        // 判断是否有参数缺失
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(p-> System.out.println(p.getDefaultMessage()));
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }
        return serviceActivityIncubationService.insertActivityIncubation(activityIncubation);

    }

    @RequestMapping(value = "/activityIncubation", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除孵化活动", notes = "参数包括：孵化活动id")
    public ResultBean<?> deleteActivityIncubationById(@NonNull Integer activityIncubationId){

        return serviceActivityIncubationService.deleteActivityIncubationById(activityIncubationId);

    }

    @RequestMapping(value = "/activityIncubation", method = RequestMethod.PUT)
    @ApiOperation(value = "根据主键修改孵化活动", notes = "参数包括：孵化活动对象，id必填，其他选填")
    public ResultBean<?> updateActivityIncubationById(@RequestBody  ActivityIncubation activityIncubation){

        return serviceActivityIncubationService.updateActivityIncubationById(activityIncubation);

    }

    @RequestMapping(value = "/activityIncubation/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索孵化活动", notes = "参数包括：页数，每页数量，活动类型id, 活动名称")
    public ResultBean<PageInfo<?>> searchActivityIncubationListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer activityCategoryId, String activityName){

        return serviceActivityIncubationService.searchActivityIncubationListByPage(pageNum, pageSize, activityCategoryId, activityName);

    }

    @RequestMapping(value = "/activityIncubation/activityCategory", method = RequestMethod.GET)
    @ApiOperation(value = "获取活动类别列表", notes = "参数包括：无")
    public ResultBean<List<ActivityCategory>> getActivityIncubationList(){

        return systemActivityCategoryService.getActivityCategoryList();

    }


}
