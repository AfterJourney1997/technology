package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.RoomGardenMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomGardenService")
public class RoomGardenServiceImpl implements RoomGardenService {

    private final RoomGardenMapper roomGardenMapper;

    @Autowired
    public RoomGardenServiceImpl(RoomGardenMapper roomGardenMapper) {
        this.roomGardenMapper = roomGardenMapper;
    }

    @Override
    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<RoomGarden> roomGardensList = roomGardenMapper.selectWithBuildingByPage();
        return new ResultBean<>(roomGardensList);

    }

    @Override
    public ResultBean<?> deleteRoomGardenById(Integer gardenRoomId) {
        roomGardenMapper.deleteByPrimaryKey(gardenRoomId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateRoomGardenById(RoomGarden roomGarden) {
        roomGardenMapper.updateRoomGradenDynamic(roomGarden);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> insertRoomGarden(List<RoomGarden> roomGardenList) {
        roomGardenMapper.insertRoomGardenForeach(roomGardenList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<RoomGarden>> searchRoomGarden(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<RoomGarden> roomGardenList = roomGardenMapper.searchByPage(buildingId, status, roomName);
        return new ResultBean<>(roomGardenList);

    }

}
