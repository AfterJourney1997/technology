package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DeclareAwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/outcomeManage/achievement")
@Api(tags = "成果管理/企业成果接口", value = "EnterpriseAchievementController")
public class EnterpriseAchievementController {
    private final DeclareAwardService declareAwardService;

    @Autowired
    public EnterpriseAchievementController(DeclareAwardService declareAwardService) {
        this.declareAwardService = declareAwardService;
    }

    @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有的企业", notes = "参数包括：无")
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {
        return declareAwardService.getAllEnterprise();
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ApiOperation(value = "根据企业显示企业成果列表", notes = "参数包括：页数,每页数量,企业的cId")
    public ResultBean<PageInfo<?>> getEnterpriseDeclareAwardByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer cId) throws IOException {
        return declareAwardService.getEnterpriseDeclareAwardByPage(pageNum, pageSize, cId);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页企业成果列表", notes = "参数包括：页数,每页数量")
    public ResultBean<PageInfo<?>> getDeclareAwardAllByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) throws IOException {
        return declareAwardService.getDeclareAwardAllByPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除企业成果", notes = "参数：企业成果对象的dId主键")
    public ResultBean deleteDeclareAward(Integer dId) throws IOException {
        return declareAwardService.deleteDeclareAward(dId);
    }
}
