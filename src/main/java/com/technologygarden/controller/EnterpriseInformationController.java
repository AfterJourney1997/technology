package com.technologygarden.controller;


import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.util.FilUploadUtils;
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
    @Autowired
    public EnterpriseInformationController(EnterpriseInformationService enterpriseInformationService) {
        this.enterpriseInformationService = enterpriseInformationService;
    }
    // 企业入住申请提交
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "企业入住申请提交", notes = "参数包括：EnterpriseInformation对象包含当前登录对象infoid")
    public ResultBean<Integer> insertApplicationAdmission(EnterpriseInformation enterpriseInformation) throws IOException {
        System.out.println("%%%%%%%%"+enterpriseInformation);
        return enterpriseInformationService.updateByPrimaryKey(enterpriseInformation);
    }
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业信息对象EnterpriseInformation", notes = "参数包括：当前登录对象的infoid")
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(@RequestBody Integer infoid) throws IOException {
        return enterpriseInformationService.getEnterpriseInformation(infoid);
    }
    @RequestMapping(value = "/information", method = RequestMethod.POST)
    @ApiOperation(value = "录入企业信息", notes = "参数包括：EnterpriseInformation对象")
    public ResultBean updateEnterpriseInformation( EnterpriseInformation enterpriseInformation) throws IOException {
        return enterpriseInformationService.updateEnterpriseInformation(enterpriseInformation);
    }
}
