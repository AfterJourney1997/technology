package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface NoticeService {

    ResultBean<Page<Notice>> getNoticeListByPage(Integer pageNum, Integer pageSize);

    ResultBean publishNotice(Notice notice);

    ResultBean deleteNoticeById(Integer noticeId);

    ResultBean updateNoticeById(Notice notice);

    ResultBean<Page<Notice>> searchNoticeByPage(Integer pageNum, Integer pageSize, String title, Integer status);
}
