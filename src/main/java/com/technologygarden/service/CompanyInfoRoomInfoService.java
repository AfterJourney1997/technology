package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;

import java.util.List;

public interface CompanyInfoRoomInfoService {

    ResultBean<List<Room>> getCompanyRoomListByCompanyId(Integer companyId);

    ResultBean<List<CompanyRoomDevice>> getCompanyRoomDeviceByRoomIdCompanyId(Integer roomId, Integer companyId);

}
