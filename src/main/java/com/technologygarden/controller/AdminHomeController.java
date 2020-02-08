package com.technologygarden.controller;

import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EnterpriseApprovalService;
import com.technologygarden.service.NoticeService;
import com.technologygarden.service.RoleService;
import com.technologygarden.service.RoomCompanyService;
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
    private final NoticeService noticeService;
    private final RoleService roleService;
    private final RoomCompanyService roomCompanyService;

    @Autowired
    public AdminHomeController(EnterpriseApprovalService enterpriseApprovalService, NoticeService noticeService, RoleService roleService, RoomCompanyService roomCompanyService) {
        this.enterpriseApprovalService = enterpriseApprovalService;
        this.noticeService = noticeService;
        this.roleService = roleService;
        this.roomCompanyService = roomCompanyService;
    }

    @RequestMapping(value = "/sidebar/noApprovalCompany", method = RequestMethod.GET)
    @ApiOperation(value = "获取已申请但未审批的企业数量", notes = "参数包括：无")
    public ResultBean<String> getNoApprovalCompanyNum(){

        return enterpriseApprovalService.getNoApprovalCompanyNum();

    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前为发布状态的公告", notes = "参数包括：无")
    public ResultBean<Notice> getNoticePublished(){

        return noticeService.getNoticePublished();

    }

    @RequestMapping(value = "/companyNum", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前系统中企业数量", notes = "参数包括：无")
    public ResultBean<String> getCompanyNum(){

        return roleService.getCompanyNum();

    }

    @RequestMapping(value = "/roomNum", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前系统中房间数量", notes = "参数包括：无")
    public ResultBean<String> getRoomNum(){

        return roomCompanyService.getRoomNum();

    }


}
