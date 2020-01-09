package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Cooperation;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CooperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/service/consociation")
@Api(tags = "孵化服务/管理员端校企合作接口", value = "SchoolEnterpriseController")
public class SchoolEnterpriseController {

    private final CooperationService cooperationService;

    @Autowired
    public SchoolEnterpriseController(CooperationService cooperationService) {
        this.cooperationService = cooperationService;
    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的企业", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {
        return cooperationService.getAllEnterprise();
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页管理员端合作列表", notes = "参数包括：页数,每页数量")
    public ResultBean<PageInfo<?>> getCooperationByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return cooperationService.getCooperationByManage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增合作", notes = "参数包括：cooperation对象，企业传选择的cId")
    public ResultBean insertCooperation(@RequestBody Cooperation cooperation) {
        return cooperationService.insertManageCooperation(cooperation);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改合作", notes = "参数包括：修改后的cooperation对象，企业传选择的cId")
    public ResultBean updateCooperation(@RequestBody Cooperation cooperation) {
        return cooperationService.updateCooperation(cooperation);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除合作", notes = "参数包括：当前cooperation对象id")
    public ResultBean deleteCooperation(Integer id) {
        return cooperationService.deleteCooperation(id);
    }
}
