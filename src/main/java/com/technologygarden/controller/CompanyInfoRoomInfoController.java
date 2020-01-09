package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.entity.Room;
import com.technologygarden.service.CompanyInfoRoomInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/enterprise/roomInfo")
@Api(tags = "企业房间信息接口", value = "CompanyInfoRoomInfoController")
public class CompanyInfoRoomInfoController {

    private final CompanyInfoRoomInfoService companyInfoRoomInfoService;

    @Autowired
    public CompanyInfoRoomInfoController(CompanyInfoRoomInfoService companyInfoRoomInfoService) {
        this.companyInfoRoomInfoService = companyInfoRoomInfoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "根据企业id获取企业房间列表", notes = "参数包括：企业id")
    public ResultBean<List<Room>> getCompanyRoomList(@NonNull Integer companyId){

        return companyInfoRoomInfoService.getCompanyRoomListByCompanyId(companyId);

    }

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ApiOperation(value = "根据房间id获取企业房间设备列表", notes = "参数包括：页码，页面大小，房间id（房间id为空是查询当前登录企业账号全部房间的设备）")
    public ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomId(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer roomId){

        Subject currentUser = SecurityUtils.getSubject();
        Role role = (Role) currentUser.getPrincipal();
        return companyInfoRoomInfoService.getCompanyRoomDeviceByRoomIdCompanyId(pageNum, pageSize, roomId, role.getEnterpriseInformation().getCId());

    }
}
