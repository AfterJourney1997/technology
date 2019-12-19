package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ChargeMapper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.Charge;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.ChargeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChargeService")
public class ChargeServiceImpl implements ChargeService {
    private final ChargeMapper chargeMapper;
    private final RoomMapper roomMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    public ChargeServiceImpl(ChargeMapper chargeMapper, RoomMapper roomMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.chargeMapper = chargeMapper;
        this.roomMapper = roomMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> selectChargeByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Charge> charges=chargeMapper.selectChargeByPage();
        for(Charge charge:charges){
            if(charge.getCid()!=null){
                charge.setCName(enterpriseInformationMapper.selectByPrimaryKey(charge.getCid()).getCName());
            }
            if(charge.getRoomid()!=null){
                charge.setRoomName(roomMapper.selectByPrimaryKey(charge.getRoomid()).getRName());
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(charges);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getCompanyAll() {
        return new ResultBean<>(enterpriseInformationMapper.selectAll());
    }

    @Override
    public ResultBean<List<Room>> getRoomBycId(Integer cId) {
        return new ResultBean<>(roomMapper.selectRoomByCompanyId(cId));
    }

    @Override
    public ResultBean insertCharge(Charge charge) {
        return new ResultBean(chargeMapper.insert(charge));
    }

    @Override
    public ResultBean updateCharge(Charge charge) {
        return new ResultBean(chargeMapper.updateByPrimaryKey(charge));
    }

    @Override
    public ResultBean deleteCharge(Integer id) {
        return new ResultBean(chargeMapper.deleteByPrimaryKey(id));
    }

    @Override
    public ResultBean payCharge(Integer id) {
        Charge charge=chargeMapper.selectByPrimaryKey(id);
        charge.setStatus(1);
        return new ResultBean(chargeMapper.updateByPrimaryKey(charge));
    }
}
