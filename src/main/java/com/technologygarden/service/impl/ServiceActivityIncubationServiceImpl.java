package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ActivityIncubationMapper;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ServiceActivityIncubationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceActivityIncubationService")
public class ServiceActivityIncubationServiceImpl implements ServiceActivityIncubationService {

    private final ActivityIncubationMapper activityIncubationMapper;

    @Autowired
    public ServiceActivityIncubationServiceImpl(ActivityIncubationMapper activityIncubationMapper) {
        this.activityIncubationMapper = activityIncubationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getActivityIncubationListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityIncubation> activityIncubationList = activityIncubationMapper.selectActivityIncubationListByPage();
        PageInfo<?> pageInfo = new PageInfo<>(activityIncubationList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<?> insertActivityIncubation(ActivityIncubation activityIncubation) {

        // 文件上传


        activityIncubationMapper.insert(activityIncubation);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteActivityIncubationById(Integer activityIncubationId) {

        // 文件删除

        activityIncubationMapper.deleteByPrimaryKey(activityIncubationId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateActivityIncubationById(ActivityIncubation activityIncubation) {

        activityIncubationMapper.updateByIdDynamic(activityIncubation);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchActivityIncubationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityIncubation> activityIncubationList = activityIncubationMapper.searchActivityIncubationListByPage(activityCategoryId, activityName);
        PageInfo<?> pageInfo = new PageInfo<>(activityIncubationList);
        return new ResultBean<>(pageInfo);


    }
}
