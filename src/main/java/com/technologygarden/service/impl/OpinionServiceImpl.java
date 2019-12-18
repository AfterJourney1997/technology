package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.OpinionMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.OpinionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("OpinionService")
public class OpinionServiceImpl implements OpinionService {
    private final OpinionMapper opinionMapper;
    private final RoomMapper roomMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;

    public OpinionServiceImpl(OpinionMapper opinionMapper, RoomMapper roomMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.opinionMapper = opinionMapper;
        this.roomMapper = roomMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getOpinionByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Opinion> opinionList=opinionMapper.getOpinionByPage(cId);
        for(Opinion opinion:opinionList){
            if(opinion.getRoomId()!=null){
                opinion.setRoomName(roomMapper.selectByPrimaryKey(opinion.getRoomId()).getRName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(opinionList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertOpinionByPage(Opinion opinion) {
        opinion.setCId(opinion.getInfoid());
        opinion.setDatetime(new Date());
        return new ResultBean(opinionMapper.insert(opinion));
    }

    @Override
    public ResultBean deleteOpinion(Integer id) {
        return new ResultBean(opinionMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean<List<Room>> getRoomBycId(Integer infoid) {
        return new ResultBean<>(roomMapper.selectRoomByCompanyId(infoid));
    }

    //管理员端，设施维护
    @Override
    public ResultBean<PageInfo<?>> getOpinionAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Opinion> opinionList=opinionMapper.getOpinionAllByPage();
        for(Opinion opinion:opinionList){
            if(opinion.getRoomId()!=null){
                opinion.setRoomName(roomMapper.selectByPrimaryKey(opinion.getRoomId()).getRName());
            }
            if(opinion.getCId()!=null){
                opinion.setCName(enterpriseInformationMapper.selectByPrimaryKey(opinion.getCId()).getCName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(opinionList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean updateOpinion(Opinion opinion) {
        return new ResultBean(opinionMapper.updateByPrimaryKey(opinion));
    }
}
