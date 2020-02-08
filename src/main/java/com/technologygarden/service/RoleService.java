package com.technologygarden.service;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;

public interface RoleService {

    ResultBean<Role> getRoleByAccount(String account);

    ResultBean<?> updateEnterprisePassword( Integer infoid, String newPassword);

    ResultBean<Role> getRoleByPhone(String account);

    ResultBean<String> getCompanyNum();
}
