package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.AwardsMapper;
import com.technologygarden.dao.DeclareAwardMapper;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.AwardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AwardsService")
public class AwardsServiceImpl implements AwardsService {
    private final AwardsMapper awardsMapper;
    private final DeclareAwardMapper declareAwardMapper;
    @Autowired
    public AwardsServiceImpl(AwardsMapper awardsMapper, DeclareAwardMapper declareAwardMapper) {
        this.awardsMapper = awardsMapper;
        this.declareAwardMapper = declareAwardMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getAwardsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Awards> awardsList=awardsMapper.selectByPage();
        PageInfo<?> pageInfo = new PageInfo<>(awardsList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertAwards(Awards awards) {
        return new ResultBean(awardsMapper.insert(awards));
    }

    @Override
    public ResultBean updateAwards(Awards awards) {
        return new ResultBean(awardsMapper.updateByPrimaryKey(awards));
    }

    @Override
    public ResultBean deleteAwards(Integer id) {
        List<DeclareAward> declareAwardList=declareAwardMapper.selectByaId(id);
        if(declareAwardList.size()>0){
            return new ResultBean<>(ResultStatus.DELETE_ERROR.getCode(), ResultStatus.DELETE_ERROR.getMessage());
        }
        return new ResultBean(awardsMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<PageInfo<?>> searchAwardsName(Integer pageNum, Integer pageSize,String awardsName) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Awards> awardsList=awardsMapper.searchAwardsName(awardsName);
        PageInfo<?> pageInfo = new PageInfo<>(awardsList);
        return new ResultBean<>(pageInfo);
    }
}
