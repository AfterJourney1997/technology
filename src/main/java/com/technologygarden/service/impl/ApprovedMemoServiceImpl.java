package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ApprovedMemoMapper;
import com.technologygarden.entity.ApprovedMemo;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.ApprovedMemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ApprovedMemoService")
public class ApprovedMemoServiceImpl implements ApprovedMemoService {
    private final ApprovedMemoMapper approvedMemoMapper;
    @Autowired
    public ApprovedMemoServiceImpl(ApprovedMemoMapper approvedMemoMapper) {
        this.approvedMemoMapper = approvedMemoMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getApprovedMemoBycId(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ApprovedMemo> approvedMemos=approvedMemoMapper.getApprovedMemoBycId(cId);
        PageInfo<?> pageInfo = new PageInfo<>(approvedMemos);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> deleteApprovedMemoById(Integer id) {
        return new ResultBean<>(approvedMemoMapper.deleteByPrimaryKey(id));
    }
}
