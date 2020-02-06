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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private final RoleMapper roleMapper;

    @Autowired
    public LoginServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public ResultBean<?> checkLogin(String account, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        // shiro登录判断
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {

            log.info("该账号不存在 ---> [{}]", account);
            return new ResultBean<>(ResultStatus.UNKNOWN_ACCOUNT_ERROR);
        } catch (IncorrectCredentialsException e) {

            log.info("该账号输入的密码错误 ---> [{}]", account);
            return new ResultBean<>(ResultStatus.PASSWORD_ERROR);
        } catch (AuthenticationException e) {

            log.error("登录错误 ---> [{}]", e.getMessage());
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 登录成功后重新获取携带信息的subject
        subject = SecurityUtils.getSubject();

        JSONObject jsonObject = packageRoleInfo(subject);

        return new ResultBean<>(jsonObject);
    }

    @Override
    public ResultBean<?> updatePassword(Integer id, String oldPassword, String newPassword) {

        Role role = roleMapper.selectByPrimaryKey(id);

        if(!oldPassword.equals(role.getPassword())){
            log.warn("修改密码失败，输入的旧密码错误 ---> [{}]", role);
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

        Role roleSubject = (Role) subject.getPrincipal();

        // 直接使用subject中的role对象会影响到subject中的信息
        Role role = Role.builder()
                .id(roleSubject.getId())
                .account(roleSubject.getAccount())
                .role(roleSubject.getRole())
                .infoid(roleSubject.getInfoid())
                .build();


        JSONObject jsonObject = new JSONObject();

        // role为1该账户为管理员，向前端传对应的权限列表
        // role字段的值须为数组，前端根据此数组的length判别账号身份，length > 1即为管理员
        // 为了保证不出问题，在数组中添加2个空string
        if(roleSubject.getRole() == 1){
            List<String> rightsList = roleSubject.getRightsList()
                                                    .stream()
                                                    .map(Rights::getRPerms)
                                                    .collect(Collectors.toList());

            rightsList.add("");
            rightsList.add("");
            jsonObject.put("role", rightsList);

        }

        // role为2该账户为企业，向前端传对应角色信息和企业信息
        if(roleSubject.getRole() == 2){

            String[] roleArray = new String[1];
            jsonObject.put("company", roleSubject.getEnterpriseInformation());

            // status为2该企业通过审批，角色为companyAgreed，否则角色为companyNoAgreed
            if(roleSubject.getEnterpriseInformation().getCStatus() == 2){
                roleArray[0] = "companyAgreed";
            }else {
                roleArray[0] = "companyNoAgreed";
            }
            jsonObject.put("role", roleArray);

        }

        jsonObject.put("sessionId", subject.getSession().getId());
        jsonObject.put("account", role);

        return jsonObject;
    }

}
