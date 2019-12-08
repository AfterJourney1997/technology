package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomService;
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
@Api(tags = "房产管理接口", value = "RoomController")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @RequestMapping(value = "/garden", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取园区房产信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize){

        return roomService.getRoomGardenByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除园区房产", notes = "参数包括：园区房产主键id")
    public ResultBean deleteRoomGardenById(Integer gardenRoomId){

        return roomService.deleteRoomGardenById(gardenRoomId);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.PUT)
    @ApiOperation(value = "修改园区房产", notes = "参数包括：园区房产对象，无需修改的值设空")
    public ResultBean updateRoomGardenById(@RequestBody RoomGarden roomGarden){

        return roomService.updateRoomGardenById(roomGarden);

    }

    @RequestMapping(value = "/garden", method = RequestMethod.POST)
    @ApiOperation(value = "新增园区房产", notes = "参数包括：园区房产列表，一个也需放进list中")
    public ResultBean insertRoomGarden(@RequestBody List<RoomGarden> roomGardenList){

        return roomService.insertRoomGarden(roomGardenList);

    }

    @RequestMapping(value = "/garden/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索园区房产", notes = "参数包括：页数、每页数量、房区id、状态id、房间名称，不需要的填空即可")
    public ResultBean<Page<RoomGarden>> searchRoomGarden(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer buildingId, Integer status, String roomName){

        return roomService.searchRoomGarden(pageNum, pageSize, buildingId, status, roomName);

    }





    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取企业房产信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<Page<RoomGarden>> getCompanyGardenByPage(Integer pageNum, Integer pageSize){

        return roomService.getRoomGardenByPage(pageNum, pageSize);

    }

}
