package com.technologygarden.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.DeclareAwardService;
import com.technologygarden.util.FilUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin
@RestController
//@RequiresPermissions("/declareAward/manage")
@RequestMapping(value = "/declareAward")
@Api(tags = "奖项申报接口", value = "DeclareAwardController")
public class DeclareAwardController {
    private final DeclareAwardService declareAwardService;

    @Autowired
    public DeclareAwardController(DeclareAwardService declareAwardService) {
        this.declareAwardService = declareAwardService;
    }

    @RequestMapping(value = "/manage/degree", method = RequestMethod.GET)
    @ApiOperation(value = "获取奖项", notes = "参数包括：无")
    public ResultBean getDegreeAll() {
        return declareAwardService.getDegreeAll();
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "获取分页奖项申报列表", notes = "参数包括：页数,每页数量,当前登录对象的infoid,均必填")
    public ResultBean<PageInfo<?>> getDeclareAwardByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid) throws IOException {
        return declareAwardService.getDeclareAwardByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "添加申报奖项", notes = "参数：文件数组，奖项申报对象，infoid写到DeclareAward对象里")
    public ResultBean insertDeclareAward(MultipartFile[] blFile, String declareAward) throws IOException {
        DeclareAward declareAward1 = JSONArray.parseObject(declareAward, DeclareAward.class);
        return declareAwardService.insertDeclareAward(blFile, declareAward1);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "取消申报奖项", notes = "参数：奖项申报对象的dId主键")
    public ResultBean deleteDeclareAward(Integer dId) throws IOException {
        return declareAwardService.deleteDeclareAward(dId);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按名称搜索申报奖项", notes = "参数：页数,每页数量,当前登陆用户的infoid，搜索内容")
    public ResultBean searchDeclareAward(Integer pageNum, Integer pageSize, Integer cId, String search) throws IOException {
        return declareAwardService.searchDeclareAward(pageNum, pageSize, cId, search);
    }

}
