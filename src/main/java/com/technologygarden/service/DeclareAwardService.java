package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface DeclareAwardService {
    public ResultBean<PageInfo<?>> getDeclareAwardByPage(Integer pageNum, Integer pageSize, Integer cId) throws IOException;
    public ResultBean insertDeclareAward(MultipartFile[] blFile, DeclareAward declareAward) throws IOException;
    public ResultBean deleteDeclareAward(Integer dId) throws IOException;
    public ResultBean getDegreeAll();
    public ResultBean searchDeclareAward(Integer pageNum, Integer pageSize,Integer cId,String search);

    public ResultBean<List<EnterpriseInformation>> getAllEnterprise();
    public ResultBean<PageInfo<?>> getDeclareAwardAllByPage(Integer pageNum, Integer pageSize);
    public ResultBean<PageInfo<?>> getEnterpriseDeclareAwardByPage( Integer pageNum, Integer pageSize, Integer cId);
}
