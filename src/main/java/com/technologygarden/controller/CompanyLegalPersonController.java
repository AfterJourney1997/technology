package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CompanyLegalPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/company/legalPerson")
//@RequiresPermissions("/company/legalPerson")
@Api(tags = "入驻企业 / 企业法人接口", value = "CompanyLegalPersonService")
public class CompanyLegalPersonController {

    private final CompanyLegalPersonService companyLegalPersonService;

    @Autowired
    public CompanyLegalPersonController(CompanyLegalPersonService companyLegalPersonService) {
        this.companyLegalPersonService = companyLegalPersonService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取企业法人列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getLegalPersonByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return companyLegalPersonService.getLegalPersonByPage(pageNum, pageSize);

    }

/*    @RequestMapping(value = "/legalPerson", method = RequestMethod.POST)
    @ApiOperation(value = "新增企业法人", notes = "参数包括：法人对象")
    public ResultBean<?> insertLegalPersonByPage(@RequestBody LegalPerson legalPerson){

        return companyLegalPersonService.insertLegalPerson(legalPerson);

    }*/


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索企业法人列表", notes = "参数包括：页数，每页数量，法人名称，企业名称")
    public ResultBean<PageInfo<?>> searchLegalPersonByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String legalPersonName, String companyName){

        return companyLegalPersonService.searchLegalPersonByPage(pageNum, pageSize, legalPersonName, companyName);

    }
}
