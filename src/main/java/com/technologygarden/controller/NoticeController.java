package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Notice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
//@RequiresPermissions("/notice")
@RequestMapping(value = "/notice")
@Api(tags = "通知公告 / 公告管理接口", value = "NoticeController")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取公告列表", notes = "参数包括：页数，每页数量")
    public ResultBean<PageInfo<?>> getNoticeListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return noticeService.getNoticeListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增公告并发布", notes = "参数包括：公告对象，默认status为1并修改其他公告状态为0（0为历史公告，1为当前公告）")
    public ResultBean<?> publishNotice(@RequestBody Notice notice) {

        return noticeService.publishNotice(notice);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除公告", notes = "参数包括：公告id")
    public ResultBean<?> deleteNoticeById(@NonNull Integer noticeId) {

        return noticeService.deleteNoticeById(noticeId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改公告", notes = "参数包括：公告对象，其中公告id为必填项")
    public ResultBean<?> updateNoticeById(@RequestBody Notice notice) {

        return noticeService.updateNoticeById(notice);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索公告", notes = "参数包括：页数、每页数量、公告标题、公告状态")
    public ResultBean<PageInfo<?>> searchNoticeByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String title, Integer status) {

        return noticeService.searchNoticeByPage(pageNum, pageSize, title, status);

    }
}
