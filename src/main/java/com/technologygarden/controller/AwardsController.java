package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AwardsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/system/awards")
@Api(tags = "系统配置/奖项管理接口", value = "AwardsController")
public class AwardsController {
    private final AwardsService awardsService;

    @Autowired
    public AwardsController(AwardsService awardsService) {
        this.awardsService = awardsService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取奖项列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getAwardsByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return awardsService.getAwardsByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "添加奖项", notes = "参数：奖项对象")
    public ResultBean insertAwards(@RequestBody Awards awards) {
        return awardsService.insertAwards(awards);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改奖项", notes = "参数：修改后的奖项对象，id和aName")
    public ResultBean updateAwards(@RequestBody Awards awards) {
        return awardsService.updateAwards(awards);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除奖项", notes = "参数：要删除奖项的id")
    public ResultBean updateAwards(Integer id) {
        return awardsService.deleteAwards(id);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按名称搜索奖项", notes = "参数包括：页数，每页数量，奖项名称")
    public ResultBean<PageInfo<?>> searchAwardsByName(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String awardsName) {
        return awardsService.searchAwardsName(pageNum, pageSize, awardsName);
    }
}
