package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.AssetBuildingService;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.RoomCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequiresPermissions("/room/company")
@RequestMapping(value = "/room")
@Api(tags = "房产管理 / 企业房产接口", value = "RoomCompanyController")
public class RoomCompanyController {

    private final RoomCompanyService roomCompanyService;
    private final EnterpriseInformationService enterpriseInformationService;
    private final AssetBuildingService assetBuildingService;

    @Autowired
    public RoomCompanyController(RoomCompanyService roomCompanyService, EnterpriseInformationService enterpriseInformationService, AssetBuildingService assetBuildingService) {
        this.roomCompanyService = roomCompanyService;
        this.enterpriseInformationService = enterpriseInformationService;
        this.assetBuildingService = assetBuildingService;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取企业房产信息列表", notes = "参数包括：页数，每页数量（rStatus为房产状态，1为空闲，2为入驻）")
    public ResultBean<PageInfo<?>> getCompanyGardenByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return roomCompanyService.getRoomCompanyByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/company", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除企业房产", notes = "参数包括：企业房产主键id")
    public ResultBean<?> deleteCompanyGardenById(Integer companyRoomId) {

        return roomCompanyService.deleteRoomCompanyById(companyRoomId);

    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    @ApiOperation(value = "修改企业房产", notes = "参数包括：企业房产对象，无需修改的值设空")
    public ResultBean<?> updateCompanyGardenById(@RequestBody Room roomCompany) {

        return roomCompanyService.updateRoomCompanyById(roomCompany);

    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "新增企业房产", notes = "参数包括：企业房产list，一个也需放进list中")
    public ResultBean<?> insertCompanyGarden(@RequestBody List<Room> roomCompanyList) {

        return roomCompanyService.insertRoomCompany(roomCompanyList);

    }

    @RequestMapping(value = "/company/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索企业房产", notes = "参数包括：页数、每页数量、房区id、状态id、房间名称，不需要的填空即可")
    public ResultBean<PageInfo<?>> searchCompanyGarden(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer buildingId, Integer status, String roomName) {

        return roomCompanyService.searchRoomCompany(pageNum, pageSize, buildingId, status, roomName);

    }

    @RequestMapping(value = "/company/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部企业信息列表包括管委会", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getEnterpriseInformationList() {

        return enterpriseInformationService.getEnterpriseInformationListWithCommittee();

    }

    @RequestMapping(value = "/company/building", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部房区的列表", notes = "参数包括：无")
    public ResultBean<List<Building>> getBuildingList() {

        return assetBuildingService.getAllBuilding();

    }
}
