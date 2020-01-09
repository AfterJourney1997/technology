package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Charge;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.ChargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage")
@Api(tags = "园区管理/相关收费接口", value = "ChargeController")
public class ChargeController {
    private final ChargeService chargeService;

    @Autowired
    public ChargeController(ChargeService chargeService) {
        this.chargeService = chargeService;
    }

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    @ApiOperation(value = "获取相关收费列表", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> selectChargeByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return chargeService.selectChargeByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/charge/company", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有企业列表", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getCompanyAll() {
        return chargeService.getCompanyAll();
    }

    @RequestMapping(value = "/charge/room", method = RequestMethod.GET)
    @ApiOperation(value = "获取企业对应的房间列表", notes = "参数包括：企业的cId")
    public ResultBean<List<Room>> getRoomBycId(Integer cId) {
        return chargeService.getRoomBycId(cId);
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    @ApiOperation(value = "新增相关收费", notes = "参数包括：收费对象，企业，房间传对应的id")
    public ResultBean insertCharge(@RequestBody Charge charge) {
        return chargeService.insertCharge(charge);
    }

    @RequestMapping(value = "/charge", method = RequestMethod.PUT)
    @ApiOperation(value = "修改相关收费", notes = "参数包括：修改后的收费对象，企业，房间传对应的id")
    public ResultBean updateCharge(@RequestBody Charge charge) {
        return chargeService.updateCharge(charge);
    }

    @RequestMapping(value = "/charge", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除相关收费", notes = "参数包括：对象的id")
    public ResultBean deleteCharge(Integer id) {
        return chargeService.deleteCharge(id);
    }

    @RequestMapping(value = "/charge/pay", method = RequestMethod.PUT)
    @ApiOperation(value = "缴纳相关收费", notes = "参数包括：缴费对象的id")
    public ResultBean payCharge(@RequestBody Map<String, String> map) {
        Integer id = Integer.parseInt(map.get("id"));
        return chargeService.payCharge(id);
    }
}
