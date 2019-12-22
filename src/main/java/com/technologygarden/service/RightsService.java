package com.technologygarden.service;

import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Rights;

import java.util.List;

public interface RightsService {

    ResultBean<List<Rights>> getRightsByRoleId(Integer roleId);

}
