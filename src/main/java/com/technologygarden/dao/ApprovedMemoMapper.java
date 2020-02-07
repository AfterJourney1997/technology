package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.ApprovedMemo;
import java.util.List;

public interface ApprovedMemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApprovedMemo record);

    ApprovedMemo selectByPrimaryKey(Integer id);

    List<ApprovedMemo> selectAll();

    Page<ApprovedMemo> getApprovedMemoBycId(Integer cId);

    int updateByPrimaryKey(ApprovedMemo record);
}