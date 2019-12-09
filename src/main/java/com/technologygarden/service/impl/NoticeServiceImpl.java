package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.NoticeMapper;
import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public ResultBean<Page<Notice>> getNoticeListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Notice> noticeList = noticeMapper.selectByPage();
        return new ResultBean<>(noticeList);
    }

    @Override
    public ResultBean publishNotice(Notice notice) {

        noticeMapper.updateStatusToZero();
        noticeMapper.insert(notice);
        return new ResultBean();

    }

    @Override
    public ResultBean deleteNoticeById(Integer noticeId) {

        noticeMapper.deleteByPrimaryKey(noticeId);
        return new ResultBean();

    }

    @Override
    public ResultBean updateNoticeById(Notice notice) {

        noticeMapper.updateNoticeByIdDynamic(notice);
        return new ResultBean();

    }

    @Override
    public ResultBean<Page<Notice>> searchNoticeByPage(Integer pageNum, Integer pageSize, String title, Integer status) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Notice> noticeList = noticeMapper.searchNoticeDynamic(title, status);
        return new ResultBean<>(noticeList);

    }
}
