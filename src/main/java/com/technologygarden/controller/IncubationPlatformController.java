package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.PlaformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/platformManage/incubation")
@Api(tags = "平台管理/孵化平台接口", value = "IncubationPlatformController")
public class IncubationPlatformController {

    private final PlaformService plaformService;

    @Autowired
    public IncubationPlatformController(PlaformService plaformService) {
        this.plaformService = plaformService;
    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的企业", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {
        return plaformService.getAllEnterprise();
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有平台申请列表", notes = "参数包括：页数，每页数量。" +
            "cName，所属企业名字，状态status:0表示未审批，1表示已审批，2表示已拒绝")
    public ResultBean<PageInfo<?>> getAllPlatformApplication(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return plaformService.selectAll(pageNum, pageSize);
    }

    @RequestMapping(value = "/plaform", method = RequestMethod.PUT)
    @ApiOperation(value = "平台操作", notes = "参数包括：当前审批的平台对象，" +
            "状态status:1表示审批，2表示拒绝")
    public ResultBean plaformOperation(@RequestBody PlatformApplication platformApplication) {
        return plaformService.plaformOperation(platformApplication);
    }

    //    @RequestMapping(value = "/plaform", method = RequestMethod.POST)
//    @ApiOperation(value = "新增平台申请", notes = "参数包括：PlatformApplication对象，企业传cId")
//    public ResultBean insertPlatformApplication(@RequestBody PlatformApplication platformApplication) {
//
//        return plaformService.insertPlatformApplication(platformApplication);
//    }
//
//    @RequestMapping(value = "/plaform", method = RequestMethod.PUT)
//    @ApiOperation(value = "平台申请修改", notes = "参数包括：PlatformApplication对象，如果修改企业，直接修改对应的cId")
//    public ResultBean updatePlatformApplication(@RequestBody  PlatformApplication platformApplication) {
//
//        return plaformService.updatePlatformApplication(platformApplication);
//    }
//
    @RequestMapping(value = "/plaform", method = RequestMethod.DELETE)
    @ApiOperation(value = "平台申请删除", notes = "参数包括：PlatformApplication对象的pId")
    public ResultBean deletePlatformApplication(Integer pId) {

        return plaformService.deletePlatformApplication(pId);
    }
}
