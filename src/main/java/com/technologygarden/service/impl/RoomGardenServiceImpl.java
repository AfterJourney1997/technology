package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public ResultBean<PageInfo<?>> getRoomGardenByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardensList = roomMapper.selectRoomGardenWithBuildingByPage();
        PageInfo<?> pageInfo = new PageInfo<>(roomGardensList);
        return new ResultBean<>(pageInfo);

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
    public ResultBean<PageInfo<?>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardenList = roomMapper.searchRoomGardenByPage(buildingId, status, roomName);
        PageInfo<?> pageInfo = new PageInfo<>(roomGardenList);
        return new ResultBean<>(pageInfo);

    }

}
