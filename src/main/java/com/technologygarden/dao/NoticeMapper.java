package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Integer id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    Page<Notice> selectByPage();

    int updateStatusToZero();

    int updateNoticeByIdDynamic(Notice notice);

    Page<Notice> searchNoticeDynamic(@Param("title") String title, @Param("status") Integer status);

    Notice selectNoticePublished();
}