package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ServiceConditionEnterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
//@RequiresPermissions("/service/conditionEnter")
@RequestMapping(value = "/service/conditionEnter")
@Api(tags = "孵化服务 / 入驻条件接口", value = "ServiceConditionEnterController")
public class ServiceConditionEnterController {

    private final ServiceConditionEnterService serviceConditionEnterService;

    @Autowired
    public ServiceConditionEnterController(ServiceConditionEnterService serviceConditionEnterService) {
        this.serviceConditionEnterService = serviceConditionEnterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取入驻条件列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getConditionEnterListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return serviceConditionEnterService.getConditionEnterListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增入驻条件", notes = "参数包括：入驻条件对象")
    public ResultBean<?> insertConditionEnter(MultipartFile file, String conditionEnter){

        ConditionEnter conditionEnterObject = JSONObject.parseObject(conditionEnter, ConditionEnter.class);
        return serviceConditionEnterService.insertConditionEnter(file, conditionEnterObject);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除入驻条件", notes = "参数包括：入驻条件主键id")
    public ResultBean<?> deleteConditionEnterById(@NonNull Integer conditionEnterId){

        return serviceConditionEnterService.deleteConditionEnterById(conditionEnterId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "根据id修改入驻条件", notes = "参数包括：入驻条件对象，id必填，修改项选填")
    public ResultBean<?> updateConditionEnterById(MultipartFile file, String conditionEnter){

        ConditionEnter conditionEnterObject = JSONObject.parseObject(conditionEnter, ConditionEnter.class);
        return serviceConditionEnterService.updateConditionEnterById(file, conditionEnterObject);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索入驻条件列表", notes = "参数包括：页数，每页数量，入驻条件标题title")
    public ResultBean<PageInfo<?>> searchConditionEnterListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String title){

        return serviceConditionEnterService.searchConditionEnterListByPage(pageNum, pageSize, title);

    }

}
