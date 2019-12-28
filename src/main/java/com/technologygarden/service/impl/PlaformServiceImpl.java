package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.PlatformApplicationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.PlatformApplication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.PlaformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlaformService")
public class PlaformServiceImpl implements PlaformService {
    private final PlatformApplicationMapper platformApplicationMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    @Autowired
    public PlaformServiceImpl(PlatformApplicationMapper platformApplicationMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.platformApplicationMapper = platformApplicationMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getPlatformApplicationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum, pageSize);
        List<PlatformApplication> platformApplication=platformApplicationMapper.selectByPage(cId);
        if(platformApplication!=null){
           for (PlatformApplication list:platformApplication){
               list.setCName(enterpriseInformationMapper.selectByPrimaryKey(list.getCId()).getCName());
           }
        }
        Page<PlatformApplication> platformList= (Page<PlatformApplication>) platformApplication;
        PageInfo<?> pageInfo = new PageInfo<>(platformList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertPlatformApplication(PlatformApplication platformApplication) {
        platformApplication.setCId(platformApplication.getInfoid());
        return new ResultBean(platformApplicationMapper.insert(platformApplication));
    }

    @Override
    public ResultBean updatePlatformApplication(PlatformApplication platformApplication) {
        platformApplication.setCId(platformApplication.getInfoid());
        return new ResultBean(platformApplicationMapper.updateByPrimaryKey(platformApplication));
    }

    @Override
    public ResultBean deletePlatformApplication(Integer pId) {
        return new ResultBean(platformApplicationMapper.deleteByPrimaryKey(pId));
    }

    @Override
    public ResultBean<PageInfo<?>> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<PlatformApplication> platformApplication=platformApplicationMapper.selectAll();
        if(platformApplication!=null){
            for (PlatformApplication list:platformApplication){
                list.setCName(enterpriseInformationMapper.selectByPrimaryKey(list.getCId()).getCName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(platformApplication);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {

        return new ResultBean<>(enterpriseInformationMapper.selectAll());
    }

    @Override
    public ResultBean plaformOperation(PlatformApplication platformApplication) {
        return new ResultBean(platformApplicationMapper.updateByPrimaryKey(platformApplication));
    }


}
