package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.CompanyInfoRoomInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/enterprise")
@Api(tags = "企业房间信息接口", value = "CompanyInfoRoomInfoController")
public class CompanyInfoRoomInfoController {

    private final CompanyInfoRoomInfoService companyInfoRoomInfoService;

    @Autowired
    public CompanyInfoRoomInfoController(CompanyInfoRoomInfoService companyInfoRoomInfoService) {
        this.companyInfoRoomInfoService = companyInfoRoomInfoService;
    }

    @GetMapping("/roomInfo")
    @ApiOperation(value = "根据企业id获取企业房间列表", notes = "参数包括：企业id")
    public ResultBean<List<Room>> getCompanyRoomList(@NonNull Integer companyId){

        return companyInfoRoomInfoService.getCompanyRoomListByCompanyId(companyId);

    }

    @GetMapping("/roomInfo/device")
    @ApiOperation(value = "根据房间id获取企业房间设备列表", notes = "参数包括：房间id")
    public ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomId(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer roomId){

        return companyInfoRoomInfoService.getCompanyRoomDeviceByRoomId(pageNum, pageSize, roomId);

    }
}
