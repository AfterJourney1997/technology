package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface MaintainService {
    public ResultBean<PageInfo<?>> getMaintainByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertMaintain(Maintain maintain);
    public ResultBean<Maintain> selectAll();
    public ResultBean updateMaintain(Maintain maintain);
    public ResultBean deleteMaintain(Integer id);
    public ResultBean<PageInfo<?>> searchMaintainByName(Integer pageNum,Integer pageSize,String maintainName);
}
