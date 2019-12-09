package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServicePolicyRelatedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/service")
@Api(tags = "孵化服务 / 相关政策接口", value = "ServicePolicyRelatedController")
public class ServicePolicyRelatedController {

    private final ServicePolicyRelatedService servicePolicyRelatedService;

    @Autowired
    public ServicePolicyRelatedController(ServicePolicyRelatedService servicePolicyRelatedService) {
        this.servicePolicyRelatedService = servicePolicyRelatedService;
    }

    @RequestMapping(value = "/policyRelated", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取相关政策列表", notes = "参数包括：页数，每页数量，均必填（prLevel为等级，1为国家，2为省市，3为园区，4为学校）")
    public ResultBean<Page<PolicyRelated>> getPolicyRelatedListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return servicePolicyRelatedService.getPolicyRelatedListByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/policyRelated", method = RequestMethod.POST)
    @ApiOperation(value = "新增相关政策", notes = "参数包括：相关政策对象")
    public ResultBean<?> insertPolicyRelated(@Valid PolicyRelated policyRelated, BindingResult errors){

        // 判断是否有参数缺失
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(p-> System.out.println(p.getDefaultMessage()));
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }
        return servicePolicyRelatedService.insertPolicyRelated(policyRelated);

    }

    @RequestMapping(value = "/policyRelated", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除相关政策", notes = "参数包括：相关政策主键id")
    public ResultBean<?> deletePolicyRelatedById(@NonNull Integer policyRelatedId){

        return servicePolicyRelatedService.deletePolicyRelatedById(policyRelatedId);

    }

    @RequestMapping(value = "/policyRelated", method = RequestMethod.PUT)
    @ApiOperation(value = "根据id修改相关政策", notes = "参数包括：相关政策对象，id必填，修改项选填")
    public ResultBean<?> updatePolicyRelatedById(PolicyRelated policyRelated){

        return servicePolicyRelatedService.updatePolicyRelatedById(policyRelated);

    }

    @RequestMapping(value = "/policyRelated/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索相关政策列表", notes = "参数包括：页数，每页数量，等级level，相关政策标题title")
    public ResultBean<Page<PolicyRelated>> searchPolicyRelatedListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer level, String title){

        return servicePolicyRelatedService.searchPolicyRelatedListByPage(pageNum, pageSize, level, title);

    }

}
