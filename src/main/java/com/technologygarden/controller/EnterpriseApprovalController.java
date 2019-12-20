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

import java.util.Map;

@CrossOrigin
@RestController
//@RequiresPermissions("/Approval/account")
@RequestMapping(value = "/service/Approval")
@Api(tags = "孵化服务/企业审批管理接口", value = "EnterpriseApprovalController")
public class EnterpriseApprovalController {

    private final EnterpriseApprovalService enterpriseApprovalService;

    @Autowired
    public EnterpriseApprovalController(RoleService roleService, EnterpriseApprovalService enterpriseApprovalService) {
        this.enterpriseApprovalService = enterpriseApprovalService;
    }

    //新增企业账号
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ApiOperation(value = "新增账号", notes = "参数包括：账号，企业名称")
    public ResultBean insertEnterpriseAccount(@RequestBody Map<String, String> param) {
        return enterpriseApprovalService.insertEnterpriseAccount(param.get("account"), param.get("enterpriseName"));
    }

    //获取所有的企业和企业账号
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的企业和企业账号", notes = "参数包括：页数,每页数量。" +
            "返回对象里的cStatus，0表示未申请，1表示已申请，2表示已同意，3表示已拒绝")
    public ResultBean<PageInfo<?>> getEnterpriseAccount(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return enterpriseApprovalService.getEnterpriseAccount(pageNum, pageSize);
    }

    //企业审批操作
    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    @ApiOperation(value = "企业审批的操作", notes = "参数包括：企业的cId,state由前端指定值：同意传2，拒绝传3")
    public ResultBean operationEnterpriseAccount(@NonNull Integer cId, @NonNull Integer state) {
        return enterpriseApprovalService.operationEnterpriseAccount(cId, state);
    }
}
