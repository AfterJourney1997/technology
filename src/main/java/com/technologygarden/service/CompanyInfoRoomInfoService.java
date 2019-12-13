package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;

import java.util.List;

public interface CompanyInfoRoomInfoService {

    ResultBean<List<Room>> getCompanyRoomListByCompanyId(Integer companyId);

    ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomId(Integer pageNum, Integer pageSize, Integer roomId);

}
