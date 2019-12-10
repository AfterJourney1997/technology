package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.DeclareAwardService;
import com.technologygarden.util.FilUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping(value = "/declareAward")
@Api(tags = "奖项申报接口", value = "DeclareAwardController")
public class DeclareAwardController {
    private final DeclareAwardService declareAwardService;
    @Autowired
    public DeclareAwardController(DeclareAwardService declareAwardService) {
        this.declareAwardService = declareAwardService;
    }
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页奖项申报列表", notes = "参数包括：页数,每页数量,当前role对象,均必填")
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Role role){
        return declareAwardService.getDeclareAwardByPage(pageNum,pageSize,role.getInfoid());
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "添加奖项", notes = "参数：奖项申报对象")
    public ResultBean insertDeclareAward(DeclareAward declareAward){
        return declareAwardService.insertDeclareAward(declareAward);
    }

}
