package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.RoomCompanyMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.entity.RoomCompany;
import com.technologygarden.service.RoomCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomCompanyService")
public class RoomCompanyServiceImpl implements RoomCompanyService {

    private final RoomMapper roomMapper;

    @Autowired
    public RoomCompanyServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public ResultBean<Page<Room>> getRoomCompanyByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardensList = roomMapper.selectRoomCompanyWithBuildingByPage();
        return new ResultBean<>(roomGardensList);

    }

    @Override
    public ResultBean<?> deleteRoomCompanyById(Integer companyRoomId) {
        roomMapper.deleteByPrimaryKey(companyRoomId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateRoomCompanyById(Room roomCompany) {
        roomMapper.updateRoomDynamic(roomCompany);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> insertRoomCompany(List<Room> roomCompanyList) {
        roomMapper.insertRoomCompanyForeach(roomCompanyList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<Room>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomCompanyList = roomMapper.searchRoomCompanyByPage(buildingId, status, roomName);
        return new ResultBean<>(roomCompanyList);

    }

}
