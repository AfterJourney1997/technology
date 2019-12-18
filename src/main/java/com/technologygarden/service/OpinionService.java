package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Opinion;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Room;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OpinionService {
    public ResultBean<PageInfo<?>> getOpinionByPage(Integer pageNum, Integer pageSize, Integer cId);
    public ResultBean insertOpinionByPage(Opinion opinion);
    public ResultBean deleteOpinion(Integer id);

    public ResultBean<List<Room>> getRoomBycId(Integer infoid);
    public ResultBean<PageInfo<?>> getOpinionAllByPage( Integer pageNum, Integer pageSize);
    public ResultBean updateOpinion(Opinion opinion);
}
