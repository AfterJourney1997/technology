package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseApprovalService;
import com.technologygarden.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
@Api(tags = "入驻企业 / 企业统计接口", value = "CompanyStatisticsController")
public class CompanyStatisticsController {
    private final EnterpriseApprovalService enterpriseApprovalService;
    private final RoleService roleService;

    @Autowired
    public CompanyStatisticsController(RoleService roleService, EnterpriseApprovalService enterpriseApprovalService, RoleService roleService1) {
        this.enterpriseApprovalService = enterpriseApprovalService;
        this.roleService = roleService;
    }

    //获取已经审批的企业和企业账号
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ApiOperation(value = "获取已经审批的企业和企业账号，status等于2的", notes = "参数包括：页数,每页数量")
    public ResultBean<PageInfo<?>> getEnterpriseStatistics(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return enterpriseApprovalService.getEnterpriseStatistics(pageNum, pageSize);
    }

    //修改企业密码
    @RequestMapping(value = "/statistics", method = RequestMethod.PUT)
    @ApiOperation(value = "修改企业密码Password", notes = "参数包括：当前企业id，新的密码")
    public ResultBean updateEnterprisePassword(@RequestBody Map<String, String> map) {
        int cId= Integer.parseInt( map.get("cId"));
        String newPassword = map.get("newPassword");
        return roleService.updateEnterprisePassword(cId, newPassword);
    }

    //根据企业名查寻
    @RequestMapping(value = "/statistics/search", method = RequestMethod.GET)
    @ApiOperation(value = "根据企业名查寻", notes = "参数包括：页数,每页数量,搜素内容")
    public ResultBean<PageInfo<?>> searchEnterpriseStatistics(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String search) {
        return enterpriseApprovalService.searchEnterpriseStatistics(pageNum, pageSize, search);
    }

    //删除企业
    @RequestMapping(value = "/statistics", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除企业", notes = "参数包括：企业cId")
    public ResultBean deleteEnterprise(Integer cId) throws IOException {
        return enterpriseApprovalService.deleteEnterprise(cId);
    }
}
