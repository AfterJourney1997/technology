package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.PlaformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/application")
@Api(tags = "平台申请接口", value = "PlaformController")
public class PlaformController {

    private final PlaformService plaformService;

    @Autowired
    public PlaformController(PlaformService plaformService) {
        this.plaformService = plaformService;
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取平台申请列表", notes = "参数包括：页数，每页数量,当前对象Role的infoid")
    public ResultBean<PageInfo<?>> getPlatformApplicationByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid) {
        return plaformService.getPlatformApplicationByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.POST)
    @ApiOperation(value = "新增平台申请", notes = "参数包括：PlatformApplication对象包含当前登录对象infoid")
    public ResultBean insertPlatformApplication(@RequestBody PlatformApplication platformApplication) {

        return plaformService.insertPlatformApplication(platformApplication);
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.PUT)
    @ApiOperation(value = "平台申请修改", notes = "参数包括：PlatformApplication对象包含当前登录对象infoid")
    public ResultBean updatePlatformApplication(@RequestBody PlatformApplication platformApplication) {

        return plaformService.updatePlatformApplication(platformApplication);
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.DELETE)
    @ApiOperation(value = "平台申请删除", notes = "参数包括：PlatformApplication对象的pId")
    public ResultBean deletePlatformApplication(Integer pId) {

        return plaformService.deletePlatformApplication(pId);
    }

}
