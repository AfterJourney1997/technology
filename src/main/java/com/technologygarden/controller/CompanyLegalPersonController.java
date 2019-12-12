package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CompanyLegalPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
//@RequiresPermissions("/company/legalPerson")
@Api(tags = "入驻企业 / 企业法人接口", value = "CompanyLegalPersonService")
public class CompanyLegalPersonController {

    private final CompanyLegalPersonService companyLegalPersonService;

    @Autowired
    public CompanyLegalPersonController(CompanyLegalPersonService companyLegalPersonService) {
        this.companyLegalPersonService = companyLegalPersonService;
    }

    @RequestMapping(value = "/legalPerson", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取企业法人列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<Page<LegalPerson>> getLegalPersonByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return companyLegalPersonService.getLegalPersonByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/legalPerson", method = RequestMethod.POST)
    @ApiOperation(value = "新增企业法人", notes = "参数包括：法人对象")
    public ResultBean<?> insertLegalPersonByPage(@RequestBody LegalPerson legalPerson){

        return companyLegalPersonService.insertLegalPerson(legalPerson);

    }
}
