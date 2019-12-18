package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ActivityIncubationMapper;
import com.technologygarden.entity.ActivityIncubation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceActivityIncubationService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service("serviceActivityIncubationService")
public class ServiceActivityIncubationServiceImpl implements ServiceActivityIncubationService {

    private final ActivityIncubationMapper activityIncubationMapper;

    @Autowired
    public ServiceActivityIncubationServiceImpl(ActivityIncubationMapper activityIncubationMapper) {
        this.activityIncubationMapper = activityIncubationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getActivityIncubationListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityIncubation> activityIncubationList = activityIncubationMapper.selectActivityIncubationListByPage();
        PageInfo<?> pageInfo = new PageInfo<>(activityIncubationList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<?> insertActivityIncubation(MultipartFile file, ActivityIncubation activityIncubation) {

        if(file == null){
            log.warn("新增孵化活动 上传文件为空。");
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 上传文件并获取携带uuid的文件名
        try {
            String saveFile = FilUploadUtils.saveFile(file);
            activityIncubation.setAiFile(saveFile);
        } catch (IOException e) {
            log.error("新增孵化活动 文件上传错误 ---> " + e.getMessage());
            return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
        }

        activityIncubationMapper.insert(activityIncubation);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteActivityIncubationById(Integer activityIncubationId) {

        // 删除文件
        ActivityIncubation activityIncubation = activityIncubationMapper.selectByPrimaryKey(activityIncubationId);
        if(!StringUtil.empty(activityIncubation.getAiFile())){

            try {
                FilUploadUtils.deleteFile(activityIncubation.getAiFile());
            } catch (IOException e) {
                log.error("删除孵化活动 文件删除错误 ---> " + e.getMessage());
            }
        }

        activityIncubationMapper.deleteByPrimaryKey(activityIncubationId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateActivityIncubationById(MultipartFile file, ActivityIncubation activityIncubation) {

        if(activityIncubation.getAiId() == null){
            log.warn("修改孵化活动 入驻条件主键缺失。");
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        // 如果修改文件，需先删除原先文件，再上传当前文件
        if(file != null){

            // 删除文件
            ActivityIncubation activityIncubationDelete = activityIncubationMapper.selectByPrimaryKey(activityIncubation.getAiId());
            if(!StringUtil.empty(activityIncubationDelete.getAiFile())){

                try {
                    FilUploadUtils.deleteFile(activityIncubationDelete.getAiFile());
                } catch (IOException e) {
                    log.error("修改孵化活动 文件删除错误 ---> " + e.getMessage());
                }
            }

            // 上传文件并获取携带uuid的文件名
            try {
                String saveFile = FilUploadUtils.saveFile(file);
                activityIncubation.setAiFile(saveFile);
            } catch (IOException e) {
                log.error("修改孵化活动 文件上传错误 ---> " + e.getMessage());
                return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
            }

        }

        activityIncubationMapper.updateByIdDynamic(activityIncubation);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchActivityIncubationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityIncubation> activityIncubationList = activityIncubationMapper.searchActivityIncubationListByPage(activityCategoryId, activityName);
        PageInfo<?> pageInfo = new PageInfo<>(activityIncubationList);
        return new ResultBean<>(pageInfo);


    }
}
