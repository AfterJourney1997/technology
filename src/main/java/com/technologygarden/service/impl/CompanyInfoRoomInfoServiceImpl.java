package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.CompanyInfoRoomInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyInfoRoomInfoService")
public class CompanyInfoRoomInfoServiceImpl implements CompanyInfoRoomInfoService {

    private final RoomMapper roomMapper;
    private final CompanyRoomDeviceMapper companyRoomDeviceMapper;

    public CompanyInfoRoomInfoServiceImpl(RoomMapper roomMapper, CompanyRoomDeviceMapper companyRoomDeviceMapper) {
        this.roomMapper = roomMapper;
        this.companyRoomDeviceMapper = companyRoomDeviceMapper;
    }

    @Override
    public ResultBean<List<Room>> getCompanyRoomListByCompanyId(Integer companyId) {

        List<Room> roomList = roomMapper.selectRoomByCompanyId(companyId);
        return new ResultBean<>(roomList);
    }

    @Override
    public ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomId(Integer pageNum, Integer pageSize, Integer roomId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<CompanyRoomDevice> companyRoomDeviceList = companyRoomDeviceMapper.selectWithInfoPageByRoomId(roomId);
        PageInfo<?> pageInfo = new PageInfo<>(companyRoomDeviceList);
        return new ResultBean<>(pageInfo);
    }
}
