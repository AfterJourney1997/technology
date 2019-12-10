package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
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
    @ApiOperation(value = "分页获取员工列表", notes = "参数包括：页数，每页数量，当前登录对象，均必填")
    public ResultBean<Page<Employee>> getEmployeeByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, Role role){
        return employeeService.selectByPage(pageNum,pageSize,role.getInfoid());
    }
    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ApiOperation(value = "新增员工", notes = "参数包括：员工对象employee包含当前登录role对象,政治面貌和学位传对应的Id")
    public ResultBean insertEmployee(Employee employee){
        return employeeService.insertEmployee(employee);
    }
    @RequestMapping(value = "/manage", method = RequestMethod.PUT)
    @ApiOperation(value = "员工信息修改", notes = "参数包括：员工对象employee,政治面貌和学位传对应的Id")
    public ResultBean updateEmployee(Employee employee){
        return employeeService.updateEmployee(employee);
    }
    @RequestMapping(value = "/manage", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除员工", notes = "参数包括：要删除员工对象对应的eId")
    public ResultBean deleteEmployee(Integer eId){
        return employeeService.deleteEmployee(eId);
    }
    @RequestMapping(value = "/manage/search", method = RequestMethod.GET)
    @ApiOperation(value = "按名称分页获取员工列表", notes = "参数包括：页数，每页数量，当前登录对象，搜索内容，均必填")
    public ResultBean<Page<Employee>> selectByNamePage(@NonNull Integer pageNum, @NonNull Integer pageSize, Role role,String employeeName){
        return employeeService.selectByNamePage(pageNum,pageSize,role.getInfoid(),employeeName);
    }
}
