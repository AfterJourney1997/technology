package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public ResultBean<PageInfo<?>> getRoomCompanyByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomGardensList = roomMapper.selectRoomCompanyWithBuildingByPage();
        PageInfo<?> pageInfo = new PageInfo<>(roomGardensList);
        return new ResultBean<>(pageInfo);

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
    public ResultBean<PageInfo<?>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Room> roomCompanyList = roomMapper.searchRoomCompanyByPage(buildingId, status, roomName);
        PageInfo<?> pageInfo = new PageInfo<>(roomCompanyList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<List<Room>> getRoomEntered() {

        // 状态2表示房间有企业入驻，1为空闲
        int status = 2;
        List<Room> roomList = roomMapper.selectRoomByStatus(status);
        return new ResultBean<>(roomList);
    }

    @Override
    public ResultBean<?> getRoomDynamic(Integer companyId, Integer buildingId) {

        // 状态2表示房间有企业入驻，1为空闲
        int status = 2;
        List<Room> roomList = roomMapper.selectRoomDynamic(status, companyId, buildingId);
        return new ResultBean<>(roomList);
    }

    @Override
    public ResultBean<String> getRoomNum() {

        return new ResultBean<>(Integer.toString(roomMapper.countAll()));
    }

}
