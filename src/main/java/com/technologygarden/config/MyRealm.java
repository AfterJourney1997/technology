package com.technologygarden.config;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Rights;
import com.technologygarden.entity.Role;
import com.technologygarden.service.RightsService;
import com.technologygarden.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RightsService rightsService;

    // 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //null usernames are invalid
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        Role role = (Role)getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> rightsSet = role.getRightsList().stream().map(Rights::getRPerms).collect(Collectors.toSet());
        System.out.println(rightsSet);
        info.setStringPermissions(rightsSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        ResultBean<Role> roleResultBean = roleService.getRoleByAccount(username);
        Role role = roleResultBean.getData();
        if(role == null){
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        ResultBean<List<Rights>> rightsList = rightsService.getRightsByRoleId(role.getId());
        role.setRightsList(rightsList.getData());

        return new SimpleAuthenticationInfo(role, roleResultBean.getData().getPassword(), getName());
    }
}
