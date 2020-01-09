package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ActivityCommunication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ServiceActivityCommunicationService;
import com.technologygarden.service.SystemActivityCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/service/activityCommunication")
@Api(tags = "孵化服务 / 交流活动接口", value = "ServiceActivityCommunicationController")
public class ServiceActivityCommunicationController {

    private final ServiceActivityCommunicationService serviceActivityCommunicationService;
    private final SystemActivityCategoryService systemActivityCategoryService;

    @Autowired
    public ServiceActivityCommunicationController(ServiceActivityCommunicationService serviceActivityCommunicationService, SystemActivityCategoryService systemActivityCategoryService) {
        this.serviceActivityCommunicationService = serviceActivityCommunicationService;
        this.systemActivityCategoryService = systemActivityCategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取交流活动列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getActivityCommunicationListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return serviceActivityCommunicationService.getActivityCommunicationListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增交流活动", notes = "参数包括：上传文件（图片），交流活动JSON")
    public ResultBean<?> insertActivityCommunication(MultipartFile file, String activityCommunication) {

        ActivityCommunication activityCommunicationObject = JSONObject.parseObject(activityCommunication, ActivityCommunication.class);
        return serviceActivityCommunicationService.insertActivityCommunication(file, activityCommunicationObject);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除交流活动", notes = "参数包括：交流活动id")
    public ResultBean<?> deleteActivityCommunicationById(@NonNull Integer activityCommunicationId) {

        return serviceActivityCommunicationService.deleteActivityCommunicationById(activityCommunicationId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "根据主键修改交流活动", notes = "参数包括：文件（选填），交流活动对象，id必填，其他选填")
    public ResultBean<?> updateActivityCommunicationById(MultipartFile file, String activityCommunication) {

        ActivityCommunication activityCommunicationObject = JSONObject.parseObject(activityCommunication, ActivityCommunication.class);
        return serviceActivityCommunicationService.updateActivityCommunicationById(file, activityCommunicationObject);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索交流活动", notes = "参数包括：页数，每页数量，活动类型id, 活动名称")
    public ResultBean<PageInfo<?>> searchActivityCommunicationListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer activityCategoryId, String activityName) {

        return serviceActivityCommunicationService.searchActivityCommunicationListByPage(pageNum, pageSize, activityCategoryId, activityName);

    }

    @RequestMapping(value = "/activityCategory", method = RequestMethod.GET)
    @ApiOperation(value = "获取活动类别列表", notes = "参数包括：无")
    public ResultBean<List<ActivityCategory>> getActivityCommunicationList() {

        return systemActivityCategoryService.getActivityCategoryList();

    }


}
