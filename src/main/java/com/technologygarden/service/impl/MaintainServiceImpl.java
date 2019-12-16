package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.MaintainMapper;
import com.technologygarden.dao.ServiceApplicationMapper;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.entity.ServiceApplication;
import com.technologygarden.service.MaintainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MaintainService")
public class MaintainServiceImpl implements MaintainService {
    private final MaintainMapper maintainMapper;
    private final ServiceApplicationMapper serviceApplicationMapper;

    public MaintainServiceImpl(MaintainMapper maintainMapper, ServiceApplicationMapper serviceApplicationMapper) {
        this.maintainMapper = maintainMapper;
        this.serviceApplicationMapper = serviceApplicationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getMaintainByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Maintain> maintains=maintainMapper.getMaintainByPage();
        PageInfo<?> pageInfo = new PageInfo<>(maintains);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertMaintain(Maintain maintain) {
        return new ResultBean(maintainMapper.insert(maintain));
    }
    @Override
    public ResultBean<Maintain> selectAll() {
        return new ResultBean(maintainMapper.selectAll());
    }

    @Override
    public ResultBean updateMaintain(Maintain maintain) {
        return new ResultBean(maintainMapper.updateByPrimaryKey(maintain));
    }

    @Override
    public ResultBean deleteMaintain(Integer id) {
        List<ServiceApplication> serviceApplication =serviceApplicationMapper.selectBymaintainId(id);
        if(serviceApplication.size()>0){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }
        return new ResultBean(maintainMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<PageInfo<?>> searchMaintainByName(Integer pageNum, Integer pageSize, String maintainName) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Maintain> maintains=maintainMapper.searchMaintainByName(maintainName);
        PageInfo<?> pageInfo = new PageInfo<>(maintains);
        return new ResultBean<>(pageInfo);
    }
}
