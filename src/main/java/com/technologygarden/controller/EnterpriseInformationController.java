package com.technologygarden.controller;

import com.technologygarden.entity.ApplicationAdmission;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.ApplicationService;
import com.technologygarden.service.EnterpriseInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RestController
@RequestMapping(value = "/enterprise")
@Api(tags = "企业信息管理接口", value = "EnterpriseInformationController")
public class EnterpriseInformationController {
    private final EnterpriseInformationService enterpriseInformationService;
    private final ApplicationService applicationService;
    @Autowired
    public EnterpriseInformationController(EnterpriseInformationService enterpriseInformationService, ApplicationService applicationService) {
        this.enterpriseInformationService = enterpriseInformationService;
        this.applicationService = applicationService;
    }
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @ApiOperation(value = "获取入住申请时的信息,转换到企业信息对象里EnterpriseInformation", notes = "参数包括：当前登录的Role对象")
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(@RequestParam("role") Role role){
        return enterpriseInformationService.getEnterpriseInformation(role);
    }
    @RequestMapping(value = "/information", method = RequestMethod.POST)
    @ApiOperation(value = "录入企业信息", notes = "参数包括：EnterpriseInformation对象包含当前登录的Role对象")
    public ResultBean updateEnterpriseInformation(@RequestBody EnterpriseInformation enterpriseInformation) throws IOException {
        enterpriseInformation.setEId(enterpriseInformation.getRole().getInfoid());
        return enterpriseInformationService.updateEnterpriseInformation(enterpriseInformation);
    }
}
