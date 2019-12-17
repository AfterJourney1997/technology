package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.entity.Rights;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "登录接口", value = "LoginController")
public class LoginController {

    private final RoleService roleService;
    private final EnterpriseInformationService enterpriseInformationService;

    @Autowired
    public LoginController(RoleService roleService, EnterpriseInformationService enterpriseInformationService) {
        this.roleService = roleService;
        this.enterpriseInformationService = enterpriseInformationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "参数包括：账号、密码，均必填")
    public ResultBean<?> login(@NonNull String account, @NonNull String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        // shiro登录判断
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {

            log.info(account + "：该账号不存在！");
            return new ResultBean<>(ResultStatus.UNKNOWN_ACCOUNT_ERROR);
        } catch (IncorrectCredentialsException e) {

            log.info(account + "：该账号输入的密码错误！");
            return new ResultBean<>(ResultStatus.PASSWORD_ERROR);
        } catch (AuthenticationException e) {

            log.error("登录错误：" + e.getMessage());
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        Role role = (Role) subject.getPrincipal();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", subject.getSession().getId());

        // role为1该账户为管理员，向前端传对应的权限列表
        if(role.getRole() == 1){
            jsonObject.put("role", "admin");
            jsonObject.put("rights", role.getRightsList()
                    .stream()
                    .map(Rights::getRPerms)
                    .collect(Collectors.toSet()));
        }

        // role为2该账户为企业，向前端传对应角色
        if(role.getRole() == 2){

            // status为2该企业通过审批，角色为companyAgreed，否则角色为companyNoAgreed
            if(role.getEnterpriseInformation().getCStatus() == 2){
                jsonObject.put("role", "companyAgreed");
            }else {
                jsonObject.put("role", "companyNoAgreed");
            }

        }

        return new ResultBean<>(jsonObject);
    }


    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {

        return "login!";

    }

}
