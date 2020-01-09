package com.technologygarden.service.impl;

import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.CompanyRoomDeviceMapper;
import com.technologygarden.dao.RoomMapper;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import com.technologygarden.service.CompanyInfoRoomInfoService;
import com.technologygarden.util.PageUtil;
import lombok.NonNull;
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
    public ResultBean<PageInfo<?>> getCompanyRoomDeviceByRoomIdCompanyId(@NonNull Integer pageNum, @NonNull Integer pageSize, Integer roomId, Integer companyId) {

        List<CompanyRoomDevice> companyRoomDeviceList = companyRoomDeviceMapper.selectWithInfoPageByRoomIdCompanyId(roomId, companyId);
        PageInfo<CompanyRoomDevice> pageInfo = PageUtil.startPage(companyRoomDeviceList, pageNum, pageSize);
        return new ResultBean<>(pageInfo);
    }
}
