package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.RoomCompany;
import com.technologygarden.entity.RoomGarden;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoomCompanyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoomCompany record);

    RoomCompany selectByPrimaryKey(Integer id);

    List<RoomCompany> selectAll();

    int updateByPrimaryKey(RoomCompany record);

    Page<RoomCompany> selectWithBuildingByPage();

    int updateRoomCompanyDynamic(RoomCompany roomCompany);

    int insertRoomCompanyForeach(List<RoomCompany> roomCompanyList);

    Page<RoomCompany> searchByPage(@Param("buildingId") Integer buildingId, @Param("status") Integer status, @Param("roomName") String roomName);

}