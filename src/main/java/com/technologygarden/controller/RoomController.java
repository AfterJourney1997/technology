package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/room")
@Api(tags = "房产管理接口", value = "RoomController")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 分页获取园区房产
    @RequestMapping(value = "/gardenRoom", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取园区房产信息列表", notes = "参数包括：页数，每页数量")
    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize){

        return roomService.getRoomGardenByPage(pageNum, pageSize);

    }

}
