package com.technologygarden.controller;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import io.swagger.annotations.Api;
import lombok.NonNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(tags = "登录接口", value = "LoginController")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBean<?> login(@NonNull String account, @NonNull String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR.getCode(), ResultStatus.PARAMETER_ERROR.getMessage());
        }

        return new ResultBean<>(subject.getSession().getId());
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin(){

        return "login!";

    }

}
