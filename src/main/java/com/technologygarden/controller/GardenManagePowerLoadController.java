package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PowerLoad;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetBuildingService;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.GardenManagePowerLoadService;
import com.technologygarden.service.RoomCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage")
@Api(tags = "园区管理 / 用电负荷接口", value = "GardenManagePowerLoadController")
public class GardenManagePowerLoadController {

    private final GardenManagePowerLoadService gardenManagePowerLoadService;
    private final RoomCompanyService roomCompanyService;
    private final AssetBuildingService assetBuildingService;
    private final EnterpriseInformationService enterpriseInformationService;

    @Autowired
    public GardenManagePowerLoadController(GardenManagePowerLoadService gardenManagePowerLoadService, RoomCompanyService roomCompanyService, AssetBuildingService assetBuildingService, EnterpriseInformationService enterpriseInformationService) {
        this.gardenManagePowerLoadService = gardenManagePowerLoadService;
        this.roomCompanyService = roomCompanyService;
        this.assetBuildingService = assetBuildingService;
        this.enterpriseInformationService = enterpriseInformationService;
    }

    @RequestMapping(value = "/powerLoad", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取用电负荷", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> getPowerLoadListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return gardenManagePowerLoadService.getPowerLoadListByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/powerLoad", method = RequestMethod.POST)
    @ApiOperation(value = "新增用电负荷", notes = "参数包括：用电负荷对象，除主键id外其他均必填（startTime必须小于endTime）")
    public ResultBean<?> insertPowerLoad(@RequestBody PowerLoad powerLoad) {

        return gardenManagePowerLoadService.insertPowerLoad(powerLoad);

    }

    @RequestMapping(value = "/powerLoad", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用电负荷", notes = "参数包括：用电负荷主键id")
    public ResultBean<?> deletePowerLoadById(@NonNull Integer powerLoadId) {

        return gardenManagePowerLoadService.deletePowerLoadById(powerLoadId);

    }

    @RequestMapping(value = "/powerLoad", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用电负荷", notes = "参数包括：用电负荷对象，主键id必填（startTime必须小于endTime）")
    public ResultBean<?> updatePowerLoadById(@RequestBody PowerLoad powerLoad) {

        return gardenManagePowerLoadService.updatePowerLoadById(powerLoad);

    }

    @RequestMapping(value = "/powerLoad/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索用电负荷", notes = "参数包括：页数，每页数量，房间id，房区id，企业名称")
    public ResultBean<PageInfo<?>> searchPowerLoad(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer roomId, Integer buildingId, String companyName) {

        return gardenManagePowerLoadService.searchPowerLoad(pageNum, pageSize, roomId, buildingId, companyName);

    }

    @RequestMapping(value = "/powerLoad/room", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部入驻状态房间", notes = "参数包括：")
    public ResultBean<?> getRoomEntered() {

        return roomCompanyService.getRoomEntered();

    }

    @RequestMapping(value = "/powerLoad/building", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部房区", notes = "参数包括：")
    public ResultBean<?> getAllBuilding() {

        return assetBuildingService.getAllBuilding();

    }

    @RequestMapping(value = "/powerLoad/company", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部企业包括管委会", notes = "参数包括：")
    public ResultBean<?> getAllCompany() {

        return enterpriseInformationService.getEnterpriseInformationListWithCommittee();

    }
}
