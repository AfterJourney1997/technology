package com.technologygarden.service;

import com.technologygarden.entity.ApplicationAdmission;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface ApplicationService {
    public ResultBean updateByPrimaryKey(ApplicationAdmission record);
    public ResultBean<ApplicationAdmission>  selectByPrimaryKey(Integer id);
}
