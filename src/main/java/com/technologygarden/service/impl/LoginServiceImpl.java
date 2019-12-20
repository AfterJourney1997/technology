package com.technologygarden.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.RoleMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.entity.Rights;
import com.technologygarden.entity.Role;
import com.technologygarden.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final RoleMapper roleMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;

    @Autowired
    public LoginServiceImpl(RoleMapper roleMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.roleMapper = roleMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean<?> checkLogin(String account, String password) {

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

        JSONObject jsonObject = packageRoleInfo(subject);

        return new ResultBean<>(jsonObject);
    }

    @Override
    public ResultBean<?> updatePassword(Integer id, String oldPassword, String newPassword) {

        Role role = roleMapper.selectByPrimaryKey(id);

        if(!oldPassword.equals(role.getPassword())){
            log.warn("修改密码失败，输入的旧密码错误 ---> " + role);
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        role.setPassword(newPassword);
        roleMapper.updateDynamic(role);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> getRoleInfo() {

        Subject subject = SecurityUtils.getSubject();
        JSONObject jsonObject = packageRoleInfo(subject);
        return new ResultBean<>(jsonObject);
    }



    // 封装登录账户的role信息
    private JSONObject packageRoleInfo(Subject subject){

        // role信息需返回前端，这里清除不必要数据
        Role role = (Role) subject.getPrincipal();
        role.setPassword(null);
        role.setRightsList(null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", subject.getSession().getId());
        jsonObject.put("account", role);

        // role为1该账户为管理员，向前端传对应的权限列表
        if(role.getRole() == 1){
            jsonObject.put("role", "admin");
            jsonObject.put("rights", role.getRightsList()
                    .stream()
                    .map(Rights::getRPerms)
                    .collect(Collectors.toSet()));
        }

        // role为2该账户为企业，向前端传对应角色信息和企业信息
        if(role.getRole() == 2){

            EnterpriseInformation enterpriseInformation = enterpriseInformationMapper.selectByPrimaryKey(role.getInfoid());
            jsonObject.put("company", enterpriseInformation);

            // status为2该企业通过审批，角色为companyAgreed，否则角色为companyNoAgreed
            if(role.getEnterpriseInformation().getCStatus() == 2){
                jsonObject.put("role", "companyAgreed");
            }else {
                jsonObject.put("role", "companyNoAgreed");
            }

        }

        return jsonObject;
    }

}
