package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.NoticeMapper;
import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getNoticeListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Notice> noticeList = noticeMapper.selectByPage();
        PageInfo<?> pageInfo = new PageInfo<>(noticeList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> publishNotice(Notice notice) {

        // status为1该公告为发布状态
        notice.setStatus(1);

        noticeMapper.updateStatusToZero();
        noticeMapper.insert(notice);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<?> deleteNoticeById(Integer noticeId) {

        noticeMapper.deleteByPrimaryKey(noticeId);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<?> updateNoticeById(Notice notice) {

        noticeMapper.updateNoticeByIdDynamic(notice);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<PageInfo<?>> searchNoticeByPage(Integer pageNum, Integer pageSize, String title, Integer status) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Notice> noticeList = noticeMapper.searchNoticeDynamic(title, status);
        PageInfo<?> pageInfo = new PageInfo<>(noticeList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<Notice> getNoticePublished() {

        Notice notice = noticeMapper.selectNoticePublished();
        return new ResultBean<>(notice);
    }

    @Override
    public ResultBean<List<Notice>> getAllNotice() {
        return new ResultBean<>(noticeMapper.selectAll());
    }
}
