package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.RoomGarden;

import java.util.List;

public interface RoomGardenService {

    ResultBean<Page<Room>> getRoomGardenByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> deleteRoomGardenById(Integer gardenRoomId);

    ResultBean<?> updateRoomGardenById(Room roomGarden);

    ResultBean<?> insertRoomGarden(List<Room> roomGardenList);

    ResultBean<Page<Room>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);


}


