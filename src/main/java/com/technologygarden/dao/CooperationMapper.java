package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Cooperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CooperationMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteCooperationBycId(Integer cId);

    int insert(Cooperation record);

    Cooperation selectByPrimaryKey(Integer id);

    List<Cooperation> selectAll();

    Page<Cooperation> selectAllByManage();

    Page<Cooperation> getCooperationByPage(Integer cId);

    int updateByPrimaryKey(Cooperation record);
}