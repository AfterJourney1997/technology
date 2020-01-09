package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import lombok.NonNull;

import java.util.List;

public interface CompanyInfoRoomInfoService {

    ResultBean<List<Room>> getCompanyRoomListByCompanyId(Integer companyId);

    ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomIdCompanyId(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer roomId, Integer companyId);

}
