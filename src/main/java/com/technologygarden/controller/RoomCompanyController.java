package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomCompany;
import com.technologygarden.service.RoomCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/room")
@Api(tags = "房产管理 / 企业房产接口", value = "RoomCompanyController")
public class RoomCompanyController {

    private final RoomCompanyService roomCompanyService;

    public RoomCompanyController(RoomCompanyService roomCompanyService) {
        this.roomCompanyService = roomCompanyService;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取企业房产信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<Page<RoomCompany>> getCompanyGardenByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return roomCompanyService.getRoomCompanyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/company", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除企业房产", notes = "参数包括：企业房产主键id")
    public ResultBean deleteCompanyGardenById(Integer companyRoomId) {

        return roomCompanyService.deleteRoomCompanyById(companyRoomId);

    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    @ApiOperation(value = "修改企业房产", notes = "参数包括：企业房产对象，无需修改的值设空")
    public ResultBean updateCompanyGardenById(@RequestBody RoomCompany roomCompany) {

        return roomCompanyService.updateRoomCompanyById(roomCompany);

    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "新增企业房产", notes = "参数包括：企业房产list，一个也需放进list中")
    public ResultBean insertCompanyGarden(@RequestBody List<RoomCompany> roomCompanyList) {

        return roomCompanyService.insertRoomCompany(roomCompanyList);

    }

    @RequestMapping(value = "/company/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索企业房产", notes = "参数包括：页数、每页数量、房区id、状态id、房间名称，不需要的填空即可")
    public ResultBean<Page<RoomCompany>> searchCompanyGarden(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer buildingId, Integer status, String roomName) {

        return roomCompanyService.searchRoomCompany(pageNum, pageSize, buildingId, status, roomName);

    }
}
