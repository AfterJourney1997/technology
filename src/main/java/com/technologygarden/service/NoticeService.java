package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;

import java.util.List;

public interface NoticeService {

    ResultBean<PageInfo<?>> getNoticeListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> publishNotice(Notice notice);

    ResultBean<?> deleteNoticeById(Integer noticeId);

    ResultBean<?> updateNoticeById(Notice notice);

    ResultBean<PageInfo<?>> searchNoticeByPage(Integer pageNum, Integer pageSize, String title, Integer status);

    ResultBean<Notice> getNoticePublished();

    ResultBean<List<Notice>> getAllNotice();
}
