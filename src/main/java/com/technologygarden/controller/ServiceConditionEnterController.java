package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceConditionEnterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/service")
@Api(tags = "孵化服务 / 入驻条件接口", value = "ServiceConditionEnterController")
public class ServiceConditionEnterController {

    private final ServiceConditionEnterService serviceConditionEnterService;

    @Autowired
    public ServiceConditionEnterController(ServiceConditionEnterService serviceConditionEnterService) {
        this.serviceConditionEnterService = serviceConditionEnterService;
    }

    @RequestMapping(value = "/conditionEnter", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取入驻条件列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<Page<ConditionEnter>> getConditionEnterListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return serviceConditionEnterService.getConditionEnterListByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/conditionEnter", method = RequestMethod.POST)
    @ApiOperation(value = "新增入驻条件", notes = "参数包括：入驻条件对象")
    public ResultBean<?> insertConditionEnter(@Valid ConditionEnter conditionEnter, BindingResult errors){

        // 判断是否有参数缺失
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(p-> System.out.println(p.getDefaultMessage()));
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }
        return serviceConditionEnterService.insertConditionEnter(conditionEnter);

    }

    @RequestMapping(value = "/conditionEnter", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除入驻条件", notes = "参数包括：入驻条件主键id")
    public ResultBean<?> deleteConditionEnterById(@NonNull Integer conditionEnterId){

        return serviceConditionEnterService.deleteConditionEnterById(conditionEnterId);

    }

    @RequestMapping(value = "/conditionEnter", method = RequestMethod.PUT)
    @ApiOperation(value = "根据id修改入驻条件", notes = "参数包括：入驻条件对象，id必填，修改项选填")
    public ResultBean<?> updateConditionEnterById(ConditionEnter conditionEnter){

        return serviceConditionEnterService.updateConditionEnterById(conditionEnter);

    }

    @RequestMapping(value = "/conditionEnter/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索入驻条件列表", notes = "参数包括：页数，每页数量，入驻条件标题title")
    public ResultBean<Page<ConditionEnter>> searchConditionEnterListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String title){

        return serviceConditionEnterService.searchConditionEnterListByPage(pageNum, pageSize, title);

    }

}
