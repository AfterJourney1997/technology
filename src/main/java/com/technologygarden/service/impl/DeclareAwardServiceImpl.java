package com.technologygarden.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.AwardsMapper;
import com.technologygarden.dao.DeclareAwardMapper;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.DeclareAwardService;
import com.technologygarden.util.FilUploadUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("DeclareAwardService")
public class DeclareAwardServiceImpl implements DeclareAwardService {
    private final DeclareAwardMapper declareAwardMapper;
    private final AwardsMapper awardsMapper;
    private final EnterpriseInformationMapper enterpriseInformationMapper;
    @Autowired
    public DeclareAwardServiceImpl(DeclareAwardMapper declareAwardMapper, AwardsMapper awardsMapper, EnterpriseInformationMapper enterpriseInformationMapper) {
        this.declareAwardMapper = declareAwardMapper;
        this.awardsMapper = awardsMapper;
        this.enterpriseInformationMapper = enterpriseInformationMapper;
    }


    @Override
    public ResultBean<PageInfo<?>> getDeclareAwardByPage(Integer pageNum, Integer pageSize, Integer cId) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.getDeclareAwardByPage(cId);
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
            String fileNameString= declareAward.getFilename();
            if(fileNameString.length()>0){
                //如果文件存在就取文件
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                declareAward.setFileNameList(fileNameList);
                declareAward.setFilePathList(filePathList);
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(declareAwardList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean insertDeclareAward(MultipartFile[] blFile,DeclareAward declareAward) throws IOException {
        String []fileNameList=new String[blFile.length];
        String UUName;
        int i=0;
        declareAward.setDatetime(new Date());//添加申请时间
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
        if(fileNameString.length()>0){
            String fileNameArray []=fileNameString.split("/");
            for(int i=0;i<fileNameArray.length;i++) {
                FilUploadUtils.deleteFile(fileNameArray[i]);
            }
        }

        return new ResultBean(declareAwardMapper.deleteByPrimaryKey(dId));
    }

    @Override
    public ResultBean getDegreeAll() {
        return new ResultBean(awardsMapper.selectAll());
    }

    @Override
    public ResultBean searchDeclareAward(Integer pageNum, Integer pageSize,Integer cId, String search) {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.searchDeclareAward(cId,search);
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
            declareAward.setCName(enterpriseInformationMapper.selectByPrimaryKey(declareAward.getCId()).getCName());
            String fileNameString= declareAward.getFilename();
            if(fileNameString.length()>0){
                //如果文件存在就取文件
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                declareAward.setFileNameList(fileNameList);
                declareAward.setFilePathList(filePathList);
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(declareAwardList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<List<EnterpriseInformation>> getAllEnterprise() {

        return new ResultBean<>(enterpriseInformationMapper.selectAll());
    }

    @Override
    public ResultBean<PageInfo<?>> getDeclareAwardAllByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.getDeclareAwardAllByPage();
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
            declareAward.setCName(enterpriseInformationMapper.selectByPrimaryKey(declareAward.getCId()).getCName());
            String fileNameString= declareAward.getFilename();
            if(fileNameString.length()>0){
                //如果文件存在就取文件
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                declareAward.setFileNameList(fileNameList);
                declareAward.setFilePathList(filePathList);
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(declareAwardList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<PageInfo<?>> getEnterpriseDeclareAwardByPage(Integer pageNum, Integer pageSize, Integer cId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<DeclareAward> declareAwardList=declareAwardMapper.getDeclareAwardByPage(cId);
        for(DeclareAward declareAward:declareAwardList){
            declareAward.setAName(awardsMapper.selectByPrimaryKey(declareAward.getAId()).getAwardsName());
            declareAward.setCName(enterpriseInformationMapper.selectByPrimaryKey(declareAward.getCId()).getCName());
            String fileNameString= declareAward.getFilename();
            if(fileNameString.length()>0){
                //图片显示
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                declareAward.setFileNameList(fileNameList);
                declareAward.setFilePathList(filePathList);
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(declareAwardList);
        return new ResultBean<>(pageInfo);
    }
}
