package com.technologygarden.service.impl;

import cn.hutool.core.util.ArrayUtil;
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
import java.util.ArrayList;
import java.util.List;

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
            String fileNameString= declareAward.getFilename();
            String fileNameArray []=fileNameString.split("/");
            List<String> fileNameList=new ArrayList<>();
            List<String> filePathList=new ArrayList<>();
            for(int i=0;i<fileNameArray.length;i++){
                filePathList.add(FilUploadUtils.getFilePath()+"\\"+fileNameArray[i]);
                fileNameList.add(FilUploadUtils.getfileName(fileNameArray[i]));
            }
            declareAward.setFileNameList(fileNameList);
            declareAward.setFilePathList(filePathList);
        }
        return new ResultBean<>(declareAwardList);
    }

    @Override
    public ResultBean insertDeclareAward(MultipartFile[] blFile,DeclareAward declareAward) throws IOException {
        String []fileNameList=new String[blFile.length];
        String UUName;
        int i=0;
        declareAward.setCId(declareAward.getInfoid());
        for (MultipartFile file:blFile){
            UUName=FilUploadUtils.saveFile(file);
            fileNameList[i]=UUName;
            i++;
        }
        String fileName = ArrayUtil.join(fileNameList, "/");
        declareAward.setFilename(fileName);
        return new ResultBean(declareAwardMapper.insert(declareAward));
    }

    @Override
    public ResultBean deleteDeclareAward(Integer dId) throws IOException {
        DeclareAward declareAward = declareAwardMapper.selectByPrimaryKey(dId);
        String fileNameString= declareAward.getFilename();
        String fileNameArray []=fileNameString.split("/");
        for(int i=0;i<fileNameArray.length;i++) {
            FilUploadUtils.deleteFile(fileNameArray[i]);
        }
        return new ResultBean(declareAwardMapper.deleteByPrimaryKey(dId));
    }
}
