package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.RoomCompany;

import java.util.List;

public interface RoomCompanyService {

    ResultBean<Page<Room>> getRoomCompanyByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> deleteRoomCompanyById(Integer companyRoomId);

    ResultBean<?> updateRoomCompanyById(Room roomCompany);

    ResultBean<?> insertRoomCompany(List<Room> roomCompanyList);

    ResultBean<Page<Room>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);

}
