package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.AwardsMapper;
import com.technologygarden.dao.DeclareAwardMapper;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DeclareAwardService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("DeclareAwardService")
public class DeclareAwardServiceImpl implements DeclareAwardService {
    private final DeclareAwardMapper declareAwardMapper;
    private final AwardsMapper awardsMapper;
    @Autowired
    public DeclareAwardServiceImpl(DeclareAwardMapper declareAwardMapper, AwardsMapper awardsMapper) {
        this.declareAwardMapper = declareAwardMapper;
        this.awardsMapper = awardsMapper;
    }


    @Override
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(Integer pageNum, Integer pageSize, Integer cId) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.getDeclareAwardByPage(cId);
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
            String filePath=FilUploadUtils.getFilePath()+"\\"+declareAward.getFilename();
            declareAward.setFilename(FilUploadUtils.getfileName(declareAward.getFilename()));
            declareAward.setFilePath(filePath);
        }
        return new ResultBean<>(declareAwardList);
    }

    @Override
    public ResultBean insertDeclareAward(DeclareAward declareAward) throws IOException {
        declareAward.setCId(declareAward.getInfoid());
        MultipartFile blFile=declareAward.getBlFile();
        String UUName=FilUploadUtils.saveFile(blFile);
        declareAward.setFilename(UUName);
        return new ResultBean(declareAwardMapper.insert(declareAward));
    }

    @Override
    public ResultBean deleteDeclareAward(Integer dId) throws IOException {
        DeclareAward declareAward = declareAwardMapper.selectByPrimaryKey(dId);
        FilUploadUtils.deleteFile(declareAward.getFilename());
        return new ResultBean(declareAwardMapper.deleteByPrimaryKey(dId));
    }
}
