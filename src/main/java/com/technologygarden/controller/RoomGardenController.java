package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.AssetBuildingService;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.RoomGardenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/room/garden")
//@RequiresPermissions("/room/garden")
@Api(tags = "房产管理 / 园区房产接口", value = "RoomGardenController")
public class RoomGardenController {

    private final RoomGardenService roomGardenService;
    private final EnterpriseInformationService enterpriseInformationService;
    private final AssetBuildingService assetBuildingService;

    @Autowired
    public RoomGardenController(RoomGardenService roomGardenService, EnterpriseInformationService enterpriseInformationService, AssetBuildingService assetBuildingService) {
        this.roomGardenService = roomGardenService;
        this.enterpriseInformationService = enterpriseInformationService;
        this.assetBuildingService = assetBuildingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取园区房产信息列表", notes = "参数包括：页数，每页数量（rStatus为房产状态，1为空闲，2为入驻）")
    public ResultBean<PageInfo<?>> getRoomGardenByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return roomGardenService.getRoomGardenByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除园区房产", notes = "参数包括：园区房产主键id")
    public ResultBean<?> deleteRoomGardenById(Integer gardenRoomId){

        return roomGardenService.deleteRoomGardenById(gardenRoomId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改园区房产", notes = "参数包括：园区房产对象，无需修改的值设空")
    public ResultBean<?> updateRoomGardenById(@RequestBody Room roomGarden){

        return roomGardenService.updateRoomGardenById(roomGarden);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增园区房产", notes = "参数包括：园区房产list，一个也需放进list中（rStatus为状态，1为空闲，2为入驻，kind为种类，1为园区房产，2为企业房产）")
    public ResultBean<?> insertRoomGarden(@RequestBody List<Room> roomGardenList){

        return roomGardenService.insertRoomGarden(roomGardenList);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索园区房产", notes = "参数包括：页数、每页数量、房区id、状态id、房间名称，不需要的填空即可")
    public ResultBean<PageInfo<?>> searchRoomGarden(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer buildingId, Integer status, String roomName){

        return roomGardenService.searchRoomGarden(pageNum, pageSize, buildingId, status, roomName);

    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部企业信息列表包括管委会", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getEnterpriseInformationList() {

        return enterpriseInformationService.getEnterpriseInformationListWithCommittee();

    }

    @RequestMapping(value = "/building", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部房区的列表", notes = "参数包括：无")
    public ResultBean<List<Building>> getBuildingList() {

        return assetBuildingService.getAllBuilding();

    }

}
