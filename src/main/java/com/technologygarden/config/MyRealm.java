package com.technologygarden.config;

import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Rights;
import com.technologygarden.entity.Role;
import com.technologygarden.service.EnterpriseInformationService;
import com.technologygarden.service.RightsService;
import com.technologygarden.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RightsService rightsService;
    @Autowired
    private EnterpriseInformationService enterpriseInformationService;

    // 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断 [鉴权]
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //null usernames are invalid
        if (principalCollection == null) {
            log.error("鉴权的参数不能为空！");
            throw new AuthorizationException("鉴权的参数不能为空！");
        }

        Role role = (Role) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roleSet = new LinkedHashSet<>();

        // role为1该账户为管理员，需将权限列表放入SimpleAuthorizationInfo中
        if (role.getRole() == 1) {

            Set<String> rightsSet = role.getRightsList().stream().map(Rights::getRPerms).collect(Collectors.toSet());
            roleSet.add("admin");
            info.setRoles(roleSet);
            info.setStringPermissions(rightsSet);
        }

        // role为2该账户为企业，需获取企业信息判断该企业是否审批通过
        if (role.getRole() == 2) {

            EnterpriseInformation companyInfo = role.getEnterpriseInformation();

            // 企业状态0为未申请，1为已申请，2为已审批，3为拒绝审批
            if (companyInfo.getCStatus() == 2) {
                roleSet.add("companyAgreed");
            } else {
                roleSet.add("companyNoAgreed");
            }
            info.setRoles(roleSet);
            Set<String> rightsSet = new HashSet<>();
            info.setStringPermissions(rightsSet);
        }

        return info;
    }

    // 定义如何获取用户信息的业务逻辑，给shiro做登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 手机号的正则表达
        String regex = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // 获取的用户名判空
        if (username == null) {
            log.warn("获取的用户名为空！");
            throw new AccountException("获取的用户名为空！");
        }

        // 获取角色信息，通过正则判断是否通过手机号登录
        ResultBean<Role> roleResultBean;
        if(username.matches(regex)){
            roleResultBean = roleService.getRoleByPhone(username);
        }else {
            roleResultBean = roleService.getRoleByAccount(username);
        }
        Role role = roleResultBean.getData();

        // 账号不存在
        if (role == null) {
            throw new UnknownAccountException(username + "：该账号不存在。");
        }

        // role为1该账户为管理员，需获取其权限列表
        if (role.getRole() == 1) {
            ResultBean<List<Rights>> rightsList = rightsService.getRightsByRoleId(role.getId());
            log.info("[{}] 管理员登录 ---> [{}]", role.getAccount(), role);
            role.setRightsList(rightsList.getData());
            log.info("[{}] 管理员登录，获取对应权限 ---> [{}]", role.getAccount(), rightsList.getData());
        }

        // role为2该账户为企业，需获取该企业信息
        if (role.getRole() == 2) {
            ResultBean<EnterpriseInformation> companyInfo = enterpriseInformationService.getEnterpriseInformationById(role.getInfoid());
            log.info("[{}] 企业登录 ---> [{}]", role.getAccount(), role);
            role.setEnterpriseInformation(companyInfo.getData());
            log.info("[{}] 企业登录，获取对应信息 ---> [{}]", role.getAccount(), companyInfo.getData());
        }

        return new SimpleAuthenticationInfo(role, role.getPassword(), role.getAccount());
    }
}
