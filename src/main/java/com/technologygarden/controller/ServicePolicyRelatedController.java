package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ServicePolicyRelatedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(value = "/service/policyRelated")
@Api(tags = "孵化服务 / 相关政策接口", value = "ServicePolicyRelatedController")
public class ServicePolicyRelatedController {

    private final ServicePolicyRelatedService servicePolicyRelatedService;

    @Autowired
    public ServicePolicyRelatedController(ServicePolicyRelatedService servicePolicyRelatedService) {
        this.servicePolicyRelatedService = servicePolicyRelatedService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取相关政策列表", notes = "参数包括：页数，每页数量，均必填（prLevel为等级，1为国家，2为省市，3为园区，4为学校）")
    public ResultBean<PageInfo<?>> getPolicyRelatedListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return servicePolicyRelatedService.getPolicyRelatedListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增相关政策", notes = "参数包括：相关政策对象")
    public ResultBean<?> insertPolicyRelated(MultipartFile file, String policyRelated){

        PolicyRelated policyRelatedObject = JSONObject.parseObject(policyRelated, PolicyRelated.class);
        return servicePolicyRelatedService.insertPolicyRelated(file, policyRelatedObject);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除相关政策", notes = "参数包括：相关政策主键id")
    public ResultBean<?> deletePolicyRelatedById(@NonNull Integer policyRelatedId){

        return servicePolicyRelatedService.deletePolicyRelatedById(policyRelatedId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "根据id修改相关政策", notes = "参数包括：文件，相关政策json")
    public ResultBean<?> updatePolicyRelatedById(MultipartFile file, String policyRelated){

        PolicyRelated policyRelatedObject = JSONObject.parseObject(policyRelated, PolicyRelated.class);
        return servicePolicyRelatedService.updatePolicyRelatedById(file, policyRelatedObject);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索相关政策列表", notes = "参数包括：页数，每页数量，等级level，相关政策标题title")
    public ResultBean<PageInfo<?>> searchPolicyRelatedListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer level, String title){

        return servicePolicyRelatedService.searchPolicyRelatedListByPage(pageNum, pageSize, level, title);

    }

}
