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
    public ResultBean<Page<DeclareAward>> getDeclareAwardByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.getDeclareAwardByPage(cId);
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
        }
        return new ResultBean<>(declareAwardList);
    }

    @Override
    public ResultBean insertDeclareAward(DeclareAward declareAward) {
        declareAward.setCId(declareAward.getRole().getInfoid());
        MultipartFile blFile=declareAward.getBlFile();
        //declareAward.setFilename(blFile.getOriginalFilename());
        try {
            FilUploadUtils.saveFile(blFile);
        }catch (Exception e){

        }
        return new ResultBean(declareAwardMapper.insert(declareAward));
    }
}
