package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Building;
import com.technologygarden.entity.ParkEnvironment;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ParkEnvironmentService {
    public ResultBean<PageInfo<?>> getParkEnvironmentByPage( Integer pageNum, Integer pageSize);
    public ResultBean<List<Building>> getAllBuilding();
    public ResultBean insertParkEnvironment(ParkEnvironment parkEnvironment);
    public ResultBean updateParkEnvironment(ParkEnvironment parkEnvironment);
    public ResultBean deleteParkEnvironment(Integer id);
}
