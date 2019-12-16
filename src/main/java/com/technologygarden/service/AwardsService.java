package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AwardsService {
    public ResultBean<PageInfo<?>> getAwardsByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertAwards(Awards awards);
    public ResultBean updateAwards(Awards awards);
    public ResultBean deleteAwards(Integer id);
    public ResultBean<PageInfo<?>> searchAwardsName(Integer pageNum, Integer pageSize,String awardsName);
}
