package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.PoliticsStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "system/politicsStatus")
@Api(tags = "系统配置/政治面貌管理接口", value = "PoliticsStatusController")
public class PoliticsStatusController {

    private final PoliticsStatusService politicsStatusService;

    @Autowired
    public PoliticsStatusController(PoliticsStatusService politicsStatusService) {
        this.politicsStatusService = politicsStatusService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取政治面貌列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getPoliticsStatusByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        return politicsStatusService.getPoliticsStatusByPage(pageNum, pageSize);

    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增政治面貌", notes = "参数包括：政治面貌对象")
    public ResultBean insertPoliticsStatus(@RequestBody PoliticsStatus politicsStatus) {
        return politicsStatusService.insertPoliticsStatus(politicsStatus);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "修改政治面貌", notes = "参数包括：政治面貌对象，包含zId,zName")
    public ResultBean updatePoliticsStatus(@RequestBody PoliticsStatus politicsStatus) {
        return politicsStatusService.updatePoliticsStatus(politicsStatus);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除政治面貌", notes = "参数包括：删除对象的zId")
    public ResultBean updatePoliticsStatus(Integer zId) {
        return politicsStatusService.deletePoliticsStatus(zId);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "根据名称搜索政治面貌", notes = "参数包括：页数，每页数量，政治面貌名称")
    public ResultBean<PageInfo<?>> searchPoliticsStatusByName(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull String politicsStatusName) {

        return politicsStatusService.searchPoliticsStatusByName(pageNum, pageSize, politicsStatusName);

    }
}
