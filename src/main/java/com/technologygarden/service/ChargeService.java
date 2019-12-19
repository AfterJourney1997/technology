package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Charge;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ChargeService {
    public ResultBean<PageInfo<?>> selectChargeByPage( Integer pageNum, Integer pageSize);
    public ResultBean<List<EnterpriseInformation>> getCompanyAll();
    public ResultBean<List<Room>> getRoomBycId(Integer cId);
    public ResultBean insertCharge(Charge charge);
    public ResultBean updateCharge(Charge charge);
    public ResultBean deleteCharge(Integer id);
    public ResultBean payCharge(Integer id);
}
