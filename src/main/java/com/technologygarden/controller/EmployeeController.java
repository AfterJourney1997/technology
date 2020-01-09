package com.technologygarden.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/employee")
@Api(tags = "员工管理接口", value = "EmployeeController")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取员工列表", notes = "参数包括：页数，每页数量，当前登录对象的infoid，均必填")
    public ResultBean<PageInfo<?>> getEmployeeByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid) throws IOException {
        System.out.println(pageNum + pageSize + infoid);
        return employeeService.selectByPage(pageNum, pageSize, infoid);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增员工", notes = "参数包括：文件数组，员工对象employee包含当前登录对象的infoid,政治面貌和学位传对应的Id")
    public ResultBean insertEmployee(MultipartFile[] blFile, String employee) throws IOException {
        Employee employee1 = JSONArray.parseObject(employee, Employee.class);
        return employeeService.insertEmployee(blFile, employee1);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "员工信息修改", notes = "参数包括：修改后的文件,员工对象employee,政治面貌和学位传对应的Id")
    public ResultBean updateEmployee(MultipartFile[] blFile, String employee) throws IOException {
        Employee employee1 = JSONArray.parseObject(employee, Employee.class);
        return employeeService.updateEmployee(blFile, employee1);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除员工", notes = "参数包括：要删除员工对象对应的eId")
    public ResultBean deleteEmployee(Integer eId) throws IOException {
        return employeeService.deleteEmployee(eId);
    }

    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按名称分页获取员工列表", notes = "参数包括：页数，每页数量，当前登录对象的infoid，搜索内容，均必填")
    public ResultBean<PageInfo<?>> selectByNamePage(@NonNull Integer pageNum, @NonNull Integer pageSize, @NonNull Integer infoid, @NonNull String employeeName) throws IOException {
        return employeeService.selectByNamePage(pageNum, pageSize, infoid, employeeName);
    }

    @RequestMapping(value = "/manage/politicsStatus", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有的政治面貌", notes = "参数包括：无")
    public ResultBean<PoliticsStatus> selectAllByPoliticsStatus() {
        return employeeService.selectAllByPoliticsStatus();
    }

    @RequestMapping(value = "/manage/degree", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有的学位", notes = "参数包括：无")
    public ResultBean<Degree> selectAllByDegree() {
        return employeeService.selectAllByDegree();
    }
}
