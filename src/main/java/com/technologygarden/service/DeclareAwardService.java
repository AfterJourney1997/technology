package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface DeclareAwardService {
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(Integer pageNum, Integer pageSize,Integer cId) throws IOException;
    public ResultBean insertDeclareAward(MultipartFile[] blFile, DeclareAward declareAward) throws IOException;
    public ResultBean deleteDeclareAward(Integer dId) throws IOException;
    public ResultBean getDegreeAll();
}
