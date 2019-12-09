package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.ApplicationAdmissionMapper;
import com.technologygarden.dao.PlatformApplicationMapper;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.PlaformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlaformService")
public class PlaformServiceImpl implements PlaformService {
    private final PlatformApplicationMapper platformApplicationMapper;
    private final ApplicationAdmissionMapper applicationAdmissionMapper;
    @Autowired
    public PlaformServiceImpl(PlatformApplicationMapper platformApplicationMapper, ApplicationAdmissionMapper applicationAdmissionMapper) {
        this.platformApplicationMapper = platformApplicationMapper;
        this.applicationAdmissionMapper = applicationAdmissionMapper;
    }

    @Override
    public ResultBean<Page<PlatformApplication>> getPlatformApplicationByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PlatformApplication> platformApplication=platformApplicationMapper.selectAll();
        if(platformApplication!=null){
           for (PlatformApplication list:platformApplication){
               list.setCName(applicationAdmissionMapper.selectByPrimaryKey(list.getCId()).getCName());
           }
        }
        Page<PlatformApplication> platformList= (Page<PlatformApplication>) platformApplication;
        return new ResultBean<>(platformList);
    }

    @Override
    public ResultBean insertPlatformApplication(PlatformApplication platformApplication) {
        platformApplication.setCId(platformApplication.getRole().getInfoid());
        return new ResultBean(platformApplicationMapper.insert(platformApplication));
    }

    @Override
    public ResultBean updatePlatformApplication(PlatformApplication platformApplication) {
        platformApplication.setCId(platformApplication.getRole().getInfoid());
        return new ResultBean(platformApplicationMapper.updateByPrimaryKey(platformApplication));
    }

    @Override
    public ResultBean deletePlatformApplication(Integer pId) {
        return new ResultBean(platformApplicationMapper.deleteByPrimaryKey(pId));
    }


}