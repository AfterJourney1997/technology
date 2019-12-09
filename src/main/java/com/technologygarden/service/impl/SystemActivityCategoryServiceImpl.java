package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.ActivityCategoryMapper;
import com.technologygarden.entity.ActivityCategory;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.SystemActivityCategoryService;
import com.technologygarden.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemActivityCategoryService")
public class SystemActivityCategoryServiceImpl implements SystemActivityCategoryService {
    
    private final ActivityCategoryMapper activityCategoryMapper;

    @Autowired
    public SystemActivityCategoryServiceImpl(ActivityCategoryMapper activityCategoryMapper) {
        this.activityCategoryMapper = activityCategoryMapper;
    }

    @Override
    public ResultBean<Page<ActivityCategory>> getSystemActivityCategoryListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityCategory> ActivityCategoryList = activityCategoryMapper.selectSystemActivityCategoryListByPage();
        return new ResultBean<>(ActivityCategoryList);
        
    }

    @Override
    public ResultBean<?> insertSystemActivityCategoryDynamic(ActivityCategory activityCategory) {

        if(StringUtil.empty(activityCategory.getAcCategory())){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }
        activityCategoryMapper.insert(activityCategory);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteSystemActivityCategoryById(Integer id) {

        activityCategoryMapper.deleteByPrimaryKey(id);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateSystemActivityCategoryById(ActivityCategory activityCategory) {

        if(activityCategory.getAcId() == null){
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR.getCode(), ResultStatus.PARAMETER_MISSING_ERROR.getMessage());
        }

        activityCategoryMapper.updateByPrimaryKey(activityCategory);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<ActivityCategory>> searchSystemActivityCategoryByPage(Integer pageNum, Integer pageSize, String category) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityCategory> ActivityCategoryList = activityCategoryMapper.searchSystemActivityCategoryByPage(category);
        return new ResultBean<>(ActivityCategoryList);

    }

    @Override
    public ResultBean<List<ActivityCategory>> getActivityCategoryList() {

        List<ActivityCategory> activityCategoryList = activityCategoryMapper.selectAll();
        return new ResultBean<>(activityCategoryList);
    }
}
