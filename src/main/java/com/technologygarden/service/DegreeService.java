package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface DegreeService {
    public ResultBean<Page<Degree>> getDegreeByPage(Integer pageNum, Integer pageSize);
    public ResultBean insertDegree(Degree degree);
    public ResultBean updateDegree(Degree degree);
    public ResultBean deleteDegree(Integer id);
    public ResultBean<Page<Degree>> searchDegreeByName(Integer pageNum,Integer pageSize,String degreeName);

}
