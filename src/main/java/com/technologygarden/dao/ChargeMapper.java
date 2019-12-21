package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Charge;
import java.util.List;

public interface ChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteChargeBycId(Integer cId);

    int insert(Charge record);

    Charge selectByPrimaryKey(Integer id);

    List<Charge> selectAll();

    Page<Charge> selectChargeByPage();

    int updateByPrimaryKey(Charge record);
}