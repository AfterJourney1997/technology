package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface DeclareAwardMapper {
    int deleteByPrimaryKey(Integer dId);

    int deleteDeclareAwardBycId(Integer cId);

    int insert(DeclareAward record);

    DeclareAward selectByPrimaryKey(Integer dId);

    List<DeclareAward> selectAll();

    Page<DeclareAward> getDeclareAwardAllByPage();

    Page<DeclareAward> searchDeclareAward(@Param("cId") Integer cId,@Param("search") String search);

    List<DeclareAward> selectByaId(Integer aId);

    Page<DeclareAward> getDeclareAwardByPage(Integer cId);

    int updateByPrimaryKey(DeclareAward record);
}