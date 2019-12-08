package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.RoomGardenMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;
import com.technologygarden.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RoomService")
public class RoomServiceImpl implements RoomService {

    private final RoomGardenMapper roomGardenMapper;

    @Autowired
    public RoomServiceImpl(RoomGardenMapper roomGardenMapper) {
        this.roomGardenMapper = roomGardenMapper;
    }

    @Override
    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<RoomGarden> roomGardensList = roomGardenMapper.selectAll();
        return new ResultBean<>(roomGardensList);

    }
}
