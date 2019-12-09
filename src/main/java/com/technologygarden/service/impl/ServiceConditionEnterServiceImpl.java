package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.ConditionEnterMapper;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceConditionEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceConditionEnterService")
public class ServiceConditionEnterServiceImpl implements ServiceConditionEnterService {

    private final ConditionEnterMapper conditionEnterMapper;

    @Autowired
    public ServiceConditionEnterServiceImpl(ConditionEnterMapper conditionEnterMapper) {
        this.conditionEnterMapper = conditionEnterMapper;
    }

    @Override
    public ResultBean<Page<ConditionEnter>> getConditionEnterListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ConditionEnter> conditionEnterList = conditionEnterMapper.selectConditionEnterListByPage();
        return new ResultBean<>(conditionEnterList);

    }

    @Override
    public ResultBean<?> insertConditionEnter(ConditionEnter conditionEnter) {

        // 上传文件

        conditionEnterMapper.insert(conditionEnter);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteConditionEnterById(Integer conditionEnterId) {

        // 删除文件

        conditionEnterMapper.deleteByPrimaryKey(conditionEnterId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateConditionEnterById(ConditionEnter conditionEnter) {

        if(conditionEnter.getCeId() == null){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }

        conditionEnterMapper.updateConditionEnterByIdDynamic(conditionEnter);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<ConditionEnter>> searchConditionEnterListByPage(Integer pageNum, Integer pageSize, String title) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ConditionEnter> conditionEnterList = conditionEnterMapper.searchConditionEnterListByPage(title);
        return new ResultBean<>(conditionEnterList);

    }
}
