package com.technologygarden.service;

import com.technologygarden.entity.ResultBean.ResultBean;
import lombok.NonNull;

import java.util.List;

public interface SystemAccountService {

    ResultBean<?> getAllMenuWithRightsByRoleId(Integer roleId);

    ResultBean<?> getAllAdmin(@NonNull Integer pageNum, @NonNull Integer pageSize);

    ResultBean<?> distributeRightsToAccount(Integer id, List<Integer> rightsList);

    ResultBean<?> updateAdminPassword(Integer id, String newPassword);

    ResultBean<?> insertAdmin(String account);

    ResultBean<?> deleteAdmin(Integer id);
}
