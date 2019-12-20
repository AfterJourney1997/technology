package com.technologygarden.service;

import com.technologygarden.entity.ResultBean.ResultBean;

public interface LoginService {

    ResultBean<?> checkLogin(String account, String password);

    ResultBean<?> updatePassword(Integer id, String oldPassword, String newPassword);

    ResultBean<?> getRoleInfo();
}
