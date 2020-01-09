package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DegreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "system/degree")
@Api(tags = "系统配置/学位管理接口", value = "DegreeController")
public class DegreeController {
    private final DegreeService degreeService;

    @Autowired
    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取学位列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getDegreeByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return degreeService.getDegreeByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增学位", notes = "参数包括：Degree对象")
    public ResultBean insertDegree(@RequestBody Degree degree) {

        return degreeService.insertDegree(degree);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改学位", notes = "参数包括：修改后的Degree对象")
    public ResultBean updateDegree(@RequestBody Degree degree) {
        return degreeService.updateDegree(degree);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除学位", notes = "参数包括：Degree对象的xId")
    public ResultBean deleteDegree(Integer xId) {
        return degreeService.deleteDegree(xId);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按名称搜索学位", notes = "参数包括：页数，每页数量，学位名称")
    public ResultBean searchDegreeByName(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String degreeName) {
        return degreeService.searchDegreeByName(pageNum, pageSize, degreeName);
    }
}
