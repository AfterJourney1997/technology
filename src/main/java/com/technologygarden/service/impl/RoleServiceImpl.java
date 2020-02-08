package com.technologygarden.service.impl;

import com.technologygarden.dao.RoleMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public ResultBean<Role> getRoleByAccount(String account) {

        Role role = roleMapper.selectByAccount(account);
        return new ResultBean<>(role);
    }

    @Override
    public ResultBean<?> updateEnterprisePassword(Integer infoid, String newPassword) {
        Role role=roleMapper.selectBycId(infoid);
        role.setPassword(newPassword);
        return new ResultBean<>(roleMapper.updateDynamic(role));
    }

    @Override
    public ResultBean<Role> getRoleByPhone(String account) {

        Role role = roleMapper.selectByPhone(account);
        return new ResultBean<>(role);
    }

    @Override
    public ResultBean<String> getCompanyNum() {

        return new ResultBean<>(Integer.toString(roleMapper.countCompanyNum()));
    }
}
