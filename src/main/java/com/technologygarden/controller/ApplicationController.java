package com.technologygarden.controller;

import com.technologygarden.entity.ApplicationAdmission;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/application")
@Api(tags = "入住申请管理接口", value = "ApplicationController")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // 企业入住申请提交
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "企业入住申请提交", notes = "参数包括：ApplicationAdmission对象包含当前登录Role对象")
    public ResultBean<Integer> insertApplicationAdmission(@RequestBody ApplicationAdmission applicationAdmission) {
        applicationAdmission.setId(applicationAdmission.getRole().getInfoid());
        return applicationService.updateByPrimaryKey(applicationAdmission);
    }
}
