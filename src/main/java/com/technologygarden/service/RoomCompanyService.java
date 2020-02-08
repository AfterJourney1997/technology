package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;

import java.util.List;

public interface RoomCompanyService {

    ResultBean<PageInfo<?>> getRoomCompanyByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> deleteRoomCompanyById(Integer companyRoomId);

    ResultBean<?> updateRoomCompanyById(Room roomCompany);

    ResultBean<?> insertRoomCompany(List<Room> roomCompanyList);

    ResultBean<PageInfo<?>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);

    ResultBean<List<Room>> getRoomEntered();

    ResultBean<?> getRoomDynamic(Integer companyId, Integer buildingId);

    ResultBean<String> getRoomNum();
}
