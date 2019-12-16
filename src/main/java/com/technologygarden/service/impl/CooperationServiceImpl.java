package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CooperationMapper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.Cooperation;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CooperationService")
public class CooperationServiceImpl implements CooperationService {
    private final CooperationMapper cooperationMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    @Autowired
    public CooperationServiceImpl(CooperationMapper cooperationMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.cooperationMapper = cooperationMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    //企业端
    @Override
    public ResultBean<PageInfo<?>> getCooperationByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Cooperation> cooperation=cooperationMapper.getCooperationByPage(cId);
        PageInfo<?> pageInfo = new PageInfo<>(cooperation);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertCooperation(Cooperation cooperation) {
        cooperation.setCId(cooperation.getInfoid());
        return new ResultBean(cooperationMapper.insert(cooperation));
    }

    @Override
    public ResultBean updateCooperation(Cooperation cooperation) {
        return new ResultBean(cooperationMapper.updateByPrimaryKey(cooperation));
    }

    @Override
    public ResultBean deleteCooperation(Integer id) {
        return new ResultBean(cooperationMapper.deleteByPrimaryKey(id));
    }

    /*
    * 管理员端
    * */

    @Override
    public ResultBean<PageInfo<?>> getCooperationByManage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Cooperation> cooperation=cooperationMapper.selectAllByManage();
        for (Cooperation coop:cooperation){
            coop.setCname(enterpriseInformationMapper.selectByPrimaryKey(coop.getCId()).getCName());
        }
        PageInfo<?> pageInfo = new PageInfo<>(cooperation);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {

        return new ResultBean<>(enterpriseInformationMapper.selectAll());
    }
    @Override
    public ResultBean insertManageCooperation(Cooperation cooperation) {
        return new ResultBean(cooperationMapper.insert(cooperation));
    }
}
