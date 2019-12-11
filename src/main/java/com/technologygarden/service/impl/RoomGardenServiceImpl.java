package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.RoomGardenMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomGardenService")
public class RoomGardenServiceImpl implements RoomGardenService {

    private final RoomMapper roomMapper;

    @Autowired
    public RoomGardenServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public ResultBean<Page<Room>> getRoomGardenByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardensList = roomMapper.selectRoomGardenWithBuildingByPage();
        return new ResultBean<>(roomGardensList);

    }

    @Override
    public ResultBean<?> deleteRoomGardenById(Integer gardenRoomId) {
        roomMapper.deleteByPrimaryKey(gardenRoomId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateRoomGardenById(Room roomGarden) {
        roomMapper.updateRoomDynamic(roomGarden);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> insertRoomGarden(List<Room> roomGardenList) {
        roomMapper.insertRoomGardenForeach(roomGardenList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<Room>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardenList = roomMapper.searchRoomGardenByPage(buildingId, status, roomName);
        return new ResultBean<>(roomGardenList);

    }

}
