package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;

import java.util.List;

public interface RoomService {

    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize);

    ResultBean deleteRoomGardenById(Integer gardenRoomId);

    ResultBean updateRoomGardenById(RoomGarden roomGarden);

    ResultBean insertRoomGarden(List<RoomGarden> roomGardenList);

    ResultBean<Page<RoomGarden>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName);

}


