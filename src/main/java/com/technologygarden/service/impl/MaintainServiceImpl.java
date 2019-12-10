package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.MaintainMapper;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.MaintainService;
import org.springframework.stereotype.Service;

@Service("MaintainService")
public class MaintainServiceImpl implements MaintainService {
    private final MaintainMapper maintainMapper;

    public MaintainServiceImpl(MaintainMapper maintainMapper) {
        this.maintainMapper = maintainMapper;
    }

    @Override
    public ResultBean<Page<Maintain>> getMaintainByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Maintain> maintains=maintainMapper.getMaintainByPage();
        return new ResultBean<>(maintains);
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
        return new ResultBean(maintainMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<Page<Maintain>> searchMaintainByName(Integer pageNum, Integer pageSize, String maintainName) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Maintain> maintains=maintainMapper.searchMaintainByName(maintainName);
        return new ResultBean<>(maintains);
    }
}
