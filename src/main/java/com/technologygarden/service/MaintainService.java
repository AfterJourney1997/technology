package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Maintain;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface MaintainService {
    public ResultBean<Page<Maintain>> getMaintainByPage(Integer pageNum,Integer pageSize);
    public ResultBean insertMaintain(Maintain maintain);
    public ResultBean<Maintain> selectAll();
    public ResultBean updateMaintain(Maintain maintain);
    public ResultBean deleteMaintain(Integer id);
    public ResultBean<Page<Maintain>> searchMaintainByName(Integer pageNum,Integer pageSize,String maintainName);
}
