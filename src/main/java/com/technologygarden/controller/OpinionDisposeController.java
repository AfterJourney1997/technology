package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;
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
@RequestMapping(value = "/gardenManage")
@Api(tags = "园区管理/设施维护(意见)接口", value = "OpinionDisposeController")
public class OpinionDisposeController {

    private final OpinionService opinionService;

    @Autowired
    public OpinionDisposeController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @RequestMapping(value = "/dispose", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有意见列表", notes = "参数包括：页数，每页数量，status:0未处理，1已处理")
    public ResultBean<PageInfo<?>> getOpinionAllByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return opinionService.getOpinionAllByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/dispose", method = RequestMethod.PUT)
    @ApiOperation(value = "审批意见", notes = "参数包括：opinion的status改为1")
    public ResultBean updateOpinion(@RequestBody Opinion opinion) {
        return opinionService.updateOpinion(opinion);
    }

    @RequestMapping(value = "/dispose", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除意见", notes = "参数包括：opinion的id")
    public ResultBean deleteOpinion(Integer id) {
        return opinionService.deleteOpinion(id);
    }
}
