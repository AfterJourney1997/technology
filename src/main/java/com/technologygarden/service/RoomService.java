package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.RoomGarden;

public interface RoomService {

    public ResultBean<Page<RoomGarden>> getRoomGardenByPage(Integer pageNum, Integer pageSize);

}
