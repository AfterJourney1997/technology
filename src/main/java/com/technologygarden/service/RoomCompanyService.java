package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomCompany;

import java.util.List;

public interface RoomCompanyService {

    ResultBean<Page<RoomCompany>> getRoomCompanyByPage(Integer pageNum, Integer pageSize);

    ResultBean deleteRoomCompanyById(Integer companyRoomId);

    ResultBean updateRoomCompanyById(RoomCompany roomCompany);

    ResultBean insertRoomCompany(List<RoomCompany> roomCompanyList);

    ResultBean<Page<RoomCompany>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);

}
