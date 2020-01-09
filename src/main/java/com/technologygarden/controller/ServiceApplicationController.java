package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.MaintainService;
import com.technologygarden.service.ServiceApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/serviceApplication")
@Api(tags = "服务申报接口", value = "ServiceApplicationController")
public class ServiceApplicationController {

    private final ServiceApplicationService serviceApplicationService;
    private final MaintainService maintainService;

    @Autowired
    public ServiceApplicationController(ServiceApplicationService serviceApplicationService, MaintainService maintainService) {
        this.serviceApplicationService = serviceApplicationService;
        this.maintainService = maintainService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页服务申请列表", notes = "参数包括：页数,每页数量,当前登录对象的infoid,均必填")
    public ResultBean<PageInfo<?>> getServiceApplicationByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer infoid) {
        return serviceApplicationService.getServiceApplicationByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/manage/room", method = RequestMethod.GET)
    @ApiOperation(value = "根据企业id获取企业的房间", notes = "参数包括：当前登录对象的infoid")
    public ResultBean<List<Room>> getRoomByInfoid(Integer infoid) {
        return serviceApplicationService.getRoomByInfoid(infoid);
    }

    @RequestMapping(value = "/manage/maintain", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的服务", notes = "参数包括：")
    public ResultBean<Maintain> getAllMaintain() {
        return maintainService.selectAll();
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增服务申请", notes = "参数包括：date申请时间，服务id，房间传对应的id，当前登录对象的infoid")
    public ResultBean insertServiceApplication(@RequestBody ServiceApplication serviceApplication) {
        serviceApplication.setCId(serviceApplication.getInfoid());
        return new ResultBean(serviceApplicationService.insertServiceApplication(serviceApplication));
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "服务修改", notes = "参数包括：修改过的serviceApplication，服务存id")
    public ResultBean updateServiceApplication(@RequestBody ServiceApplication serviceApplication) {
        return new ResultBean(serviceApplicationService.updateServiceApplication(serviceApplication));
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "服务取消", notes = "参数包括：serviceApplication对象的id")
    public ResultBean deleteServiceApplication(Integer id) {
        return new ResultBean(serviceApplicationService.deleteServiceApplication(id));
    }
}
