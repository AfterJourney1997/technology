package com.technologygarden.dao;

import com.technologygarden.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    Role selectBycId(Integer cId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}