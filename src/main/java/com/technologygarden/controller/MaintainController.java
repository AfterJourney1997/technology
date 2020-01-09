package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.MaintainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/gardenManage/maintain")
@Api(tags = "园区管理/服务属性管理接口", value = "MaintainController")
public class MaintainController {

    private final MaintainService maintainService;

    @Autowired
    public MaintainController(MaintainService maintainService) {
        this.maintainService = maintainService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取服务列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getMaintainByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return maintainService.getMaintainByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增服务", notes = "参数包括：服务对象")
    public ResultBean<Page<Maintain>> insertMaintain(@RequestBody Maintain maintain) {
        return maintainService.insertMaintain(maintain);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改服务", notes = "参数包括：修改后的服务对象")
    public ResultBean<Page<Maintain>> updateMaintain(@RequestBody Maintain maintain) {
        return maintainService.updateMaintain(maintain);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除服务", notes = "删除对象的id")
    public ResultBean<Page<Maintain>> updateMaintain(Integer id) {
        return maintainService.deleteMaintain(id);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按搜索名分页获取服务列表", notes = "参数包括：页数，每页数量，搜索的内容，均必填")
    public ResultBean<PageInfo<?>> searchMaintainByName(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String maintainName) {
        return maintainService.searchMaintainByName(pageNum, pageSize, maintainName);
    }
}
