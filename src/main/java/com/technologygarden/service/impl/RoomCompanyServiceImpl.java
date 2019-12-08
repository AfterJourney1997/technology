package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.RoomCompanyMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomCompany;
import com.technologygarden.service.RoomCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomCompanyService")
public class RoomCompanyServiceImpl implements RoomCompanyService {

    private final RoomCompanyMapper roomCompanyMapper;

    public RoomCompanyServiceImpl(RoomCompanyMapper roomCompanyMapper) {
        this.roomCompanyMapper = roomCompanyMapper;
    }

    @Override
    public ResultBean<Page<RoomCompany>> getRoomCompanyByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<RoomCompany> roomGardensList = roomCompanyMapper.selectWithBuildingByPage();
        return new ResultBean<>(roomGardensList);

    }

    @Override
    public ResultBean deleteRoomCompanyById(Integer companyRoomId) {
        roomCompanyMapper.deleteByPrimaryKey(companyRoomId);
        return new ResultBean();
    }

    @Override
    public ResultBean updateRoomCompanyById(RoomCompany roomCompany) {
        roomCompanyMapper.updateRoomCompanyDynamic(roomCompany);
        return new ResultBean();
    }

    @Override
    public ResultBean insertRoomCompany(List<RoomCompany> roomCompanyList) {
        roomCompanyMapper.insertRoomCompanyForeach(roomCompanyList);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<Page<RoomCompany>> searchRoomCompany(Integer pageNum, Integer pageSize, Integer buildingId, Integer status, String roomName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<RoomCompany> roomCompanyList = roomCompanyMapper.searchByPage(buildingId, status, roomName);
        return new ResultBean<>(roomCompanyList);

    }

}
