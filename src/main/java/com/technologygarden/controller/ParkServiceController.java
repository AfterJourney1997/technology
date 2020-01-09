package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.MaintainService;
import com.technologygarden.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage/service")
@Api(tags = "园区管理/园区服务接口", value = "ParkServiceController")
public class ParkServiceController {

    private final ServiceApplicationService serviceApplicationService;
    private final MaintainService maintainService;

    @Autowired
    public ParkServiceController(ServiceApplicationService serviceApplicationService, MaintainService maintainService) {
        this.serviceApplicationService = serviceApplicationService;
        this.maintainService = maintainService;
    }

    @RequestMapping(value = "/manage/maintain", method = RequestMethod.GET)
    @ApiOperation(value = "获取服务列表", notes = "参数包括：无")
    public ResultBean<Maintain> getAllMaintain() {
        return maintainService.selectAll();
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页显示企业申请服务列表", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> getParkServiceByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return serviceApplicationService.getParkServiceByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "受理企业申请服务列表", notes = "参数包括：当前受理的对象，status：1表示受理")
    public ResultBean operationParkService(@RequestBody ServiceApplication serviceApplication) {
        return serviceApplicationService.operationParkService(serviceApplication);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除企业申请服务", notes = "参数包括：当前对象的id")
    public ResultBean deleteParkService(Integer id) {
        return serviceApplicationService.deleteServiceApplication(id);
    }

}
