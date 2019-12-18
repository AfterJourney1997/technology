package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.ConditionEnterMapper;
import com.technologygarden.entity.ConditionEnter;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServiceConditionEnterService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service("serviceConditionEnterService")
public class ServiceConditionEnterServiceImpl implements ServiceConditionEnterService {

    private final ConditionEnterMapper conditionEnterMapper;

    @Autowired
    public ServiceConditionEnterServiceImpl(ConditionEnterMapper conditionEnterMapper) {
        this.conditionEnterMapper = conditionEnterMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getConditionEnterListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ConditionEnter> conditionEnterList = conditionEnterMapper.selectConditionEnterListByPage();
        PageInfo<?> pageInfo = new PageInfo<>(conditionEnterList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<?> insertConditionEnter(MultipartFile file, ConditionEnter conditionEnter) {

        if(file == null){
            log.warn("新增入驻条件 上传文件为空。");
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 上传文件并获取携带uuid的文件名
        try {
            String saveFile = FilUploadUtils.saveFile(file);
            conditionEnter.setCeFile(saveFile);
        } catch (IOException e) {
            log.error("新增入驻条件 文件上传错误 ---> " + e.getMessage());
            return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
        }

        conditionEnterMapper.insert(conditionEnter);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deleteConditionEnterById(Integer conditionEnterId) {

        // 删除文件
        ConditionEnter conditionEnter = conditionEnterMapper.selectByPrimaryKey(conditionEnterId);
        if(!StringUtil.empty(conditionEnter.getCeFile())){

            try {
                FilUploadUtils.deleteFile(conditionEnter.getCeFile());
            } catch (IOException e) {
                log.error("删除入驻条件 文件删除错误 ---> " + e.getMessage());
            }
        }

        conditionEnterMapper.deleteByPrimaryKey(conditionEnterId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateConditionEnterById(MultipartFile file, ConditionEnter conditionEnter) {

        if(conditionEnter.getCeId() == null){
            log.warn("修改入驻条件 入驻条件主键缺失。");
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        // 如果修改文件，需先删除原先文件，再上传当前文件
        if(file != null){

            // 删除文件
            ConditionEnter conditionEnterDelete = conditionEnterMapper.selectByPrimaryKey(conditionEnter.getCeId());
            if(!StringUtil.empty(conditionEnterDelete.getCeFile())){

                try {
                    FilUploadUtils.deleteFile(conditionEnterDelete.getCeFile());
                } catch (IOException e) {
                    log.error("修改入驻条件 文件删除错误 ---> " + e.getMessage());
                }
            }

            // 上传文件并获取携带uuid的文件名
            try {
                String saveFile = FilUploadUtils.saveFile(file);
                conditionEnter.setCeFile(saveFile);
            } catch (IOException e) {
                log.error("修改入驻条件 文件上传错误 ---> " + e.getMessage());
                return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
            }

        }

        conditionEnterMapper.updateConditionEnterByIdDynamic(conditionEnter);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchConditionEnterListByPage(Integer pageNum, Integer pageSize, String title) {

        PageHelper.startPage(pageNum, pageSize);
        Page<ConditionEnter> conditionEnterList = conditionEnterMapper.searchConditionEnterListByPage(title);
        PageInfo<?> pageInfo = new PageInfo<>(conditionEnterList);
        return new ResultBean<>(pageInfo);

    }
}
