package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ApprovedMemoService;
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
    private final ApprovedMemoService approvedMemoService;

    @Autowired
    public EnterpriseApprovalController(RoleService roleService, EnterpriseApprovalService enterpriseApprovalService, ApprovedMemoService approvedMemoService) {
        this.enterpriseApprovalService = enterpriseApprovalService;
        this.approvedMemoService = approvedMemoService;
    }

    //新增企业账号
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ApiOperation(value = "新增账号", notes = "参数包括：账号，手机号，企业名称")
    public ResultBean<?> insertEnterpriseAccount(@RequestBody Map<String, String> param) {
        return enterpriseApprovalService.insertEnterpriseAccount(param.get("account"), param.get("enterpriseName"), param.get("phone"));
    }

    //获取所有的企业和企业账号
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的企业和企业账号", notes = "参数包括：页数,每页数量。" +
            "返回对象里的cStatus，0表示未申请，1表示已申请，2表示已同意，3表示已拒绝")
    public ResultBean<PageInfo<?>> getEnterpriseAccount(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return enterpriseApprovalService.getEnterpriseAccount(pageNum, pageSize);
    }
    //获取企业的历史审批记录
    @RequestMapping(value = "/record", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业的历史审批记录", notes = "参数包括：页数,每页数量，企业的cId。" +
            "返回对象里的result，2表示同意，3表示拒绝")
    public ResultBean<PageInfo<?>> getApprovedMemoBycId(@NonNull Integer pageNum, @NonNull Integer pageSize,@NonNull Integer cId) {
        return approvedMemoService.getApprovedMemoBycId(pageNum,pageSize,cId);
    }
    //企业的历史审批记录删除
    @RequestMapping(value = "/record", method = RequestMethod.DELETE)
    @ApiOperation(value = "企业的历史审批记录删除", notes = "参数包括：审批记录的id")
    public ResultBean<?> deleteApprovedMemoById(@NonNull Integer id) {
        return approvedMemoService.deleteApprovedMemoById(id);
    }

    //企业审批操作
    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    @ApiOperation(value = "企业审批的操作", notes = "参数包括：企业的cId,state由前端指定值：同意传2，拒绝传3,审批备注comment,审批人approver")
    public ResultBean operationEnterpriseAccount(@NonNull Integer cId, @NonNull Integer state, @NonNull String comment,@NonNull String approver) {
        return enterpriseApprovalService.operationEnterpriseAccount(cId, state, comment,approver);
    }
}
