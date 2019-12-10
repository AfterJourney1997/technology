package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取意见列表", notes = "参数包括：页数，每页数量，当前Role对象的infoid")
    public ResultBean<Page<Opinion>> getOpinionByPage(@NonNull Integer pageNum, @NonNull Integer pageSize,@NonNull Integer cId){
        return opinionService.getOpinionByPage(pageNum,pageSize,cId);
    }
    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增意见", notes = "参数包括：页数，每页数量，当前Role对象")
    public ResultBean insertOpinionByPage(@RequestBody Opinion opinion){
        return opinionService.insertOpinionByPage(opinion);
    }
    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除意见", notes = "当前对象的Id")
    public ResultBean<Page> deleteOpinionByPage(@RequestBody Integer id){
        return opinionService.deleteOpinionByPage(id);
    }
}
