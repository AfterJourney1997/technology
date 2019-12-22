package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PowerLoad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerLoadMapper {
    int deleteByPrimaryKey(Integer plId);

    int insert(PowerLoad record);

    PowerLoad selectByPrimaryKey(Integer plId);

    List<PowerLoad> selectAll();

    int updateByPrimaryKey(PowerLoad record);

    Page<PowerLoad> selectAllWithRoomInfoByPage();

    int updateDynamicById(PowerLoad powerLoad);

    Page<PowerLoad> searchPowerLoadWithRoomInfoByPage(@Param("roomId") Integer roomId, @Param("buildingId") Integer buildingId, @Param("companyName") String companyName);

    int deletePowerLoadByCompanyId(@Param("companyId") Integer companyId);
}