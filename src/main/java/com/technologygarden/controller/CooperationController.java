package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Cooperation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.CooperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/cooperation")
@Api(tags = "校企合作接口", value = "CooperationController")
public class CooperationController {

    private final CooperationService cooperationService;

    @Autowired
    public CooperationController(CooperationService cooperationService) {
        this.cooperationService = cooperationService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页合作列表", notes = "参数包括：页数,每页数量,当前role对象的infoid,均必填")
    public ResultBean<PageInfo<?>> getCooperationByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid) {
        return cooperationService.getCooperationByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增合作", notes = "参数包括：cooperation对象包含当前登录对象的infoid")
    public ResultBean insertCooperation(@RequestBody Cooperation cooperation) {
        return cooperationService.insertCooperation(cooperation);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改合作", notes = "参数包括：修改后的cooperation对象")
    public ResultBean updateCooperation(@RequestBody Cooperation cooperation) {
        return cooperationService.updateCooperation(cooperation);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除合作", notes = "参数包括：当前cooperation对象id")
    public ResultBean deleteCooperation(Integer id) {
        return cooperationService.deleteCooperation(id);
    }

}
