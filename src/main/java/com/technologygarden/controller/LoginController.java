package com.technologygarden.controller;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.LoginService;
import com.technologygarden.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "登录接口", value = "LoginController")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "参数包括：账号、密码，均必填")
    public ResultBean<?> login(@NonNull String account, @NonNull String password) {

        return loginService.checkLogin(account, password);

    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", notes = "参数包括：账号主键id、旧密码，新密码，均必填")
    public ResultBean<?> updatePassword(@NonNull Integer id, @NonNull String oldPassword, @NonNull String newPassword) {

        return loginService.updatePassword(id, oldPassword, newPassword);
    }

    @RequestMapping(value = "/roleInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取登录账号的相关信息", notes = "参数包括：无，请求头携带token即可")
    public ResultBean<?> getRoleInfo() {

        return loginService.getRoleInfo();

    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public ResultBean<?> notLogin() {

        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new ResultBean<>(ResultStatus.NOT_LOGIN_ERROR);

    }

}
