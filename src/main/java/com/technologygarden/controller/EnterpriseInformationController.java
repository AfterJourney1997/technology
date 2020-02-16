package com.technologygarden.controller;

import com.alibaba.fastjson.JSONArray;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.InputDescription;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.ResultBean.FileProductResultBean;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DegreeService;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.RoleService;
import com.technologygarden.service.SystemJobTitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
//@RequiresPermissions("/enterprise/information")
@RequestMapping(value = "/company/information")
@Api(tags = "企业信息管理接口", value = "EnterpriseInformationController")
public class EnterpriseInformationController {

    private final EnterpriseInformationService enterpriseInformationService;
    private final DegreeService degreeService;
    private final SystemJobTitleService systemJobTitleService;
    private final RoleService roleService;
    private final InputDescription inputDescription;


    @Autowired
    public EnterpriseInformationController(EnterpriseInformationService enterpriseInformationService, DegreeService degreeService, SystemJobTitleService systemJobTitleService, RoleService roleService, InputDescription inputDescription) {
        this.enterpriseInformationService = enterpriseInformationService;
        this.degreeService = degreeService;
        this.systemJobTitleService = systemJobTitleService;
        this.roleService = roleService;
        this.inputDescription = inputDescription;
    }

    // 企业主要产品图片提交接口
    @RequestMapping(value = "/company/fileProduct", method = RequestMethod.POST)
    @ApiOperation(value = "企业产品图片提交接口", notes = "参数包括：当前登录对象的infoid,blFile")
    public FileProductResultBean updateByFileProduct(Integer infoid, MultipartFile[] blFile) throws IOException {
        return enterpriseInformationService.updateByFileProduct(infoid,blFile);
    }

    // 企业重新申请接口
    @RequestMapping(value = "/company/anew", method = RequestMethod.GET)
    @ApiOperation(value = "企业被拒绝后重新申请接口", notes = "参数包括：EnterpriseInformation对象包含当前登录对象的infoid")
    public ResultBean<?> companyAnew(Integer infoid) throws IOException {
        return enterpriseInformationService.companyAnew(infoid);
    }
    // 获取企业入住申请备注
    @RequestMapping(value = "/company/comment", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业入住申请备注", notes = "参数包括：无")
    public ResultBean<?> getComment() {
        return new ResultBean<>(inputDescription);
    }

    // 企业入住申请提交
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "企业入住申请提交", notes = "参数包括：EnterpriseInformation对象包含当前登录对象的infoid，和法人信息")
    public ResultBean<?> insertApplicationAdmission(MultipartFile[] blFile, String enterpriseInformation) throws IOException {
        EnterpriseInformation enterprise = JSONArray.parseObject(enterpriseInformation, EnterpriseInformation.class);
        return enterpriseInformationService.updateByPrimaryKey(blFile, enterprise);
    }
//    //企业信息完善
//    @RequestMapping(value = "/information", method = RequestMethod.POST)
//    @ApiOperation(value = "录入企业信息", notes = "参数包括：EnterpriseInformation对象，包含当前登录对象的infoid，文件信息不改动")
//    public ResultBean<?> updateEnterpriseInformation(@RequestBody EnterpriseInformation enterpriseInformation) throws IOException {
//        return enterpriseInformationService.updateEnterpriseInformation(enterpriseInformation);
//    }

    @RequestMapping(value = "/company/information", method = RequestMethod.GET)
    @ApiOperation(value = "入住申请：获取企业信息对象EnterpriseInformation", notes = "参数包括：当前登录的对象的infoid")
    public ResultBean<EnterpriseInformation> showEnterpriseInformation(Integer infoid) throws IOException {
        return enterpriseInformationService.getEnterpriseInformation(infoid);
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
    //企业通过审核后可访问的:
    //企业信息
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @ApiOperation(value = " ：获取企业信息对象EnterpriseInformation", notes = "参数包括：当前登录的对象的infoid")
    public ResultBean<EnterpriseInformation> getEnterpriseInformation(Integer infoid) throws IOException {
        return enterpriseInformationService.getEnterpriseInformation(infoid);
    }

    //修改企业密码
    @RequestMapping(value = "/information/statistics", method = RequestMethod.PUT)
    @ApiOperation(value = "修改企业密码Password", notes = "参数包括：当前企业id，新的密码")
    public ResultBean updateEnterprisePassword(@RequestBody Map<String, String> map) {
        int cId= Integer.parseInt( map.get("cId"));
        String newPassword = map.get("newPassword");
        return roleService.updateEnterprisePassword(cId, newPassword);
    }
}
