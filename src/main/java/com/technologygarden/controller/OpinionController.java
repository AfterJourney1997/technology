package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.entity.Room;
import com.technologygarden.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/opinion")
@Api(tags = "意见反馈接口", value = "OpinionController")
public class OpinionController {

    private final OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @RequestMapping(value = "/manage/room", method = RequestMethod.GET)
    @ApiOperation(value = "获取当前企业的房间列表", notes = "参数包括：当前Role对象的infoid")
    public ResultBean<List<Room>> getRoomBycId(@NonNull Integer infoid) {
        return opinionService.getRoomBycId(infoid);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取意见列表", notes = "参数包括：页数，每页数量，当前Role对象的infoid,status:0未处理，1已处理")
    public ResultBean<PageInfo<?>> getOpinionByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid) {
        return opinionService.getOpinionByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增意见", notes = "参数包括：Opinion对象，携带当前登录对象的infoid,房间传对应的房间id")
    public ResultBean insertOpinionByPage(@RequestBody Opinion opinion) {
        return opinionService.insertOpinionByPage(opinion);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除意见", notes = "当前对象的Id")
    public ResultBean<Page> deleteOpinionByPage(Integer id) {
        return opinionService.deleteOpinion(id);
    }
}
