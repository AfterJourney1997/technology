package com.technologygarden.service.impl;

import com.technologygarden.dao.RightsMapper;
import com.technologygarden.dao.RoleRightsMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Rights;
import com.technologygarden.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rightsService")
public class RightsServiceImpl implements RightsService {

    private final RightsMapper rightsMapper;
    private final RoleRightsMapper roleRightsMapper;

    @Autowired
    public RightsServiceImpl(RightsMapper rightsMapper, RoleRightsMapper roleRightsMapper) {
        this.rightsMapper = rightsMapper;
        this.roleRightsMapper = roleRightsMapper;
    }

    @Override
    public ResultBean<List<Rights>> getRightsByRoleId(Integer roleId) {

        List<Rights> rightsList = rightsMapper.selectRightsByRoleId(roleId);
        return new ResultBean<>(rightsList);
    }
}
