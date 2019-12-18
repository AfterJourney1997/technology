package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.RoomGarden;

import java.util.List;

public interface RoomGardenService {

    ResultBean<PageInfo<?>> getRoomGardenByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> deleteRoomGardenById(Integer gardenRoomId);

    ResultBean<?> updateRoomGardenById(Room roomGarden);

    ResultBean<?> insertRoomGarden(List<Room> roomGardenList);

    ResultBean<PageInfo<?>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);


}


