package com.technologygarden.controller;

import com.technologygarden.entity.Degree;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DegreeService;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.SystemJobTitleService;
import com.technologygarden.util.FilUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/enterprise")
@Api(tags = "企业信息管理接口", value = "EnterpriseInformationController")
public class EnterpriseInformationController {

    private final EnterpriseInformationService enterpriseInformationService;
    private final DegreeService degreeService;
    private final SystemJobTitleService systemJobTitleService;


    @Autowired
    public EnterpriseInformationController(EnterpriseInformationService enterpriseInformationService, DegreeService degreeService, SystemJobTitleService systemJobTitleService) {
        this.enterpriseInformationService = enterpriseInformationService;
        this.degreeService = degreeService;
        this.systemJobTitleService = systemJobTitleService;
    }

    // 企业入住申请提交
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "企业入住申请提交", notes = "参数包括：EnterpriseInformation对象包含当前登录Role对象，和法人信息")
    public ResultBean<?> insertApplicationAdmission(@RequestBody EnterpriseInformation enterpriseInformation) throws IOException {
        String UUName = FilUploadUtils.saveFile(enterpriseInformation.getBlFile());//保存文件
        enterpriseInformation.setFileName(UUName);//获取文件名
        return enterpriseInformationService.updateByPrimaryKey(enterpriseInformation);
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业信息对象EnterpriseInformation", notes = "参数包括：当前登录的Role对象的cId")
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(@RequestBody Integer cId) throws IOException {
        return enterpriseInformationService.getEnterpriseInformation(cId);
    }

    @RequestMapping(value = "/information", method = RequestMethod.POST)
    @ApiOperation(value = "录入企业信息", notes = "参数包括：EnterpriseInformation对象")
    public ResultBean<?> updateEnterpriseInformation(@RequestBody EnterpriseInformation enterpriseInformation) throws IOException {
        return enterpriseInformationService.updateEnterpriseInformation(enterpriseInformation);
    }

    @RequestMapping(value = "/company/degree", method = RequestMethod.GET)
    @ApiOperation(value = "获取学位列表", notes = "参数包括：无")
    public ResultBean<List<Degree>> getDegreeByPage() {
        return degreeService.getAllDegree();
    }

    @RequestMapping(value = "/company/jobTitle", method = RequestMethod.GET)
    @ApiOperation(value = "获取职称列表", notes = "参数包括：无")
    public ResultBean<List<JobTitle>> getSystemJobTitleListByPage() {

        return systemJobTitleService.getAllSystemJobTitle();

    }
}
