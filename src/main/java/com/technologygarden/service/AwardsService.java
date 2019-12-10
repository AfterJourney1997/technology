package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Awards;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface AwardsService {
    public ResultBean<Page<Awards>> getAwardsByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertAwards(Awards awards);
    public ResultBean updateAwards(Awards awards);
    public ResultBean deleteAwards(Integer id);
    public ResultBean<Page<Awards>> searchAwardsName(Integer pageNum, Integer pageSize,String awardsName);
}
