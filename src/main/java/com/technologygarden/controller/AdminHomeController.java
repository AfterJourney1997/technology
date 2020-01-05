package com.technologygarden.controller;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/adminHome")
@Api(tags = "管理员 / 主页接口", value = "AdminHomeController")
public class AdminHomeController {

    private final EnterpriseApprovalService enterpriseApprovalService;

    @Autowired
    public AdminHomeController(EnterpriseApprovalService enterpriseApprovalService) {
        this.enterpriseApprovalService = enterpriseApprovalService;
    }

    @RequestMapping(value = "/sidebar/noApprovalCompany", method = RequestMethod.GET)
    @ApiOperation(value = "获取已申请但未审批的企业数量", notes = "参数包括：无")
    public ResultBean<?> getNoApprovalCompanyNum(){

        return enterpriseApprovalService.getNoApprovalCompanyNum();

    }


}
