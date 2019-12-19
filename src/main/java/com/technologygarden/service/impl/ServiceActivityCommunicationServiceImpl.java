package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ActivityCommunicationMapper;
import com.technologygarden.entity.ActivityCommunication;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceActivityCommunicationService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service("serviceActivityCommunicationService")
public class ServiceActivityCommunicationServiceImpl implements ServiceActivityCommunicationService {

    private final ActivityCommunicationMapper activityCommunicationMapper;

    @Autowired
    public ServiceActivityCommunicationServiceImpl(ActivityCommunicationMapper activityCommunicationMapper) {
        this.activityCommunicationMapper = activityCommunicationMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getActivityCommunicationListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum ,pageSize);
        Page<ActivityCommunication> activityCommunicationList = activityCommunicationMapper.selectAllWithCategoryByPage();
        PageInfo<?> pageInfo = new PageInfo<>(activityCommunicationList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertActivityCommunication(MultipartFile file, ActivityCommunication activityCommunication) {

        if(file == null){
            log.warn("新增交流活动 上传文件为空。");
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 上传文件并获取携带uuid的文件名
        try {
            String saveFile = FilUploadUtils.saveFile(file);
            activityCommunication.setAcomFile(saveFile);
        } catch (IOException e) {
            log.error("新增交流活动 文件上传错误 ---> " + e.getMessage());
            return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
        }

        activityCommunicationMapper.insert(activityCommunication);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteActivityCommunicationById(Integer activityCommunicationId) {

        // 删除文件
        ActivityCommunication activityCommunication = activityCommunicationMapper.selectByPrimaryKey(activityCommunicationId);
        if(!StringUtil.empty(activityCommunication.getAcomFile())){

            try {
                FilUploadUtils.deleteFile(activityCommunication.getAcomFile());
            } catch (IOException e) {
                log.error("删除交流活动 文件删除错误 ---> " + e.getMessage());
            }
        }

        activityCommunicationMapper.deleteByPrimaryKey(activityCommunicationId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateActivityCommunicationById(MultipartFile file, ActivityCommunication activityCommunication) {

        if(activityCommunication.getAcomId() == null){
            log.warn("修改交流活动 入驻条件主键缺失。");
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        // 如果修改文件，需先删除原先文件，再上传当前文件
        if(file != null){

            // 删除文件
            ActivityCommunication activityCommunicationDelete = activityCommunicationMapper.selectByPrimaryKey(activityCommunication.getAcomId());
            if(!StringUtil.empty(activityCommunicationDelete.getAcomFile())){

                try {
                    FilUploadUtils.deleteFile(activityCommunicationDelete.getAcomFile());
                } catch (IOException e) {
                    log.error("修改交流活动 文件删除错误 ---> " + e.getMessage());
                }
            }

            // 上传文件并获取携带uuid的文件名
            try {
                String saveFile = FilUploadUtils.saveFile(file);
                activityCommunication.setAcomFile(saveFile);
            } catch (IOException e) {
                log.error("修改交流活动 文件上传错误 ---> " + e.getMessage());
                return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
            }

        }

        activityCommunicationMapper.updateDynamicById(activityCommunication);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchActivityCommunicationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName) {
        
        PageHelper.startPage(pageNum, pageSize);
        Page<ActivityCommunication> activityCommunicationList = activityCommunicationMapper.searchByPage(activityCategoryId, activityName);
        PageInfo<?> pageInfo = new PageInfo<>(activityCommunicationList);
        return new ResultBean<>(pageInfo);
    }
}
