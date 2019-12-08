package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomCompany;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomGardenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/room")
@Api(tags = "房产管理 / 园区房产接口", value = "RoomGardenController")
public class RoomGardenController {

    private final RoomGardenService roomGardenService;

    public RoomGardenController(RoomGardenService roomGardenService) {
        this.roomGardenService = roomGardenService;
    }

    @RequestMapping(value = "/garden", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取园区房产信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return roomGardenService.getRoomGardenByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除园区房产", notes = "参数包括：园区房产主键id")
    public ResultBean deleteRoomGardenById(Integer gardenRoomId){

        return roomGardenService.deleteRoomGardenById(gardenRoomId);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.PUT)
    @ApiOperation(value = "修改园区房产", notes = "参数包括：园区房产对象，无需修改的值设空")
    public ResultBean updateRoomGardenById(@RequestBody RoomGarden roomGarden){

        return roomGardenService.updateRoomGardenById(roomGarden);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.POST)
    @ApiOperation(value = "新增园区房产", notes = "参数包括：园区房产list，一个也需放进list中")
    public ResultBean insertRoomGarden(@RequestBody List<RoomGarden> roomGardenList){

        return roomGardenService.insertRoomGarden(roomGardenList);

    }

    @RequestMapping(value = "/garden/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索园区房产", notes = "参数包括：页数、每页数量、房区id、状态id、房间名称，不需要的填空即可")
    public ResultBean<Page<RoomGarden>> searchRoomGarden(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer buildingId, Integer status, String roomName){

        return roomGardenService.searchRoomGarden(pageNum, pageSize, buildingId, status, roomName);

    }



}
