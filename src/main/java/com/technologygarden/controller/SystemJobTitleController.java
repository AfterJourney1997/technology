package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.JobTitle;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemJobTitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
//@RequiresPermissions("/system/jobTitle")
@RequestMapping(value = "/system/jobTitle")
@Api(tags = "系统配置 / 职称管理接口", value = "SystemJobTitleController")
public class SystemJobTitleController {

    private final SystemJobTitleService systemJobTitleService;

    @Autowired
    public SystemJobTitleController(SystemJobTitleService systemJobTitleService) {
        this.systemJobTitleService = systemJobTitleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取职称列表", notes = "参数包括：页数、每页数量")
    public ResultBean<PageInfo<?>> getSystemJobTitleListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return systemJobTitleService.getSystemJobTitleListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增职称", notes = "参数包括：职称对象")
    public ResultBean<?> insertSystemJobTitle(@Valid @RequestBody JobTitle jobTitle, BindingResult errors) {

        // 判断是否有参数缺失
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(p-> System.out.println(p.getDefaultMessage()));
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }
        return systemJobTitleService.insertSystemJobTitle(jobTitle);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除职称", notes = "参数包括：职称主键id")
    public ResultBean<?> deleteSystemJobTitleById(@NonNull Integer jobTitleId) {

        return systemJobTitleService.deleteSystemJobTitleById(jobTitleId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改职称", notes = "参数包括：职称对象，主键必填，其他选填")
    public ResultBean<?> updateSystemJobTitleById(@RequestBody JobTitle jobTitle) {

        return systemJobTitleService.updateSystemJobTitleById(jobTitle);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取职称列表", notes = "参数包括：页数、每页数量、职称")
    public ResultBean<PageInfo<?>> searchSystemJobTitleListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String jobTitle) {

        return systemJobTitleService.searchSystemJobTitleListByPage(pageNum, pageSize, jobTitle);

    }

}
