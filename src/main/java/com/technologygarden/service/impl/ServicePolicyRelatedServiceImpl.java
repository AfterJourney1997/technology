package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.PolicyRelatedMapper;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.ServicePolicyRelatedService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service("servicePolicyRelatedService")
public class ServicePolicyRelatedServiceImpl implements ServicePolicyRelatedService {

    private final PolicyRelatedMapper policyRelatedMapper;

    @Autowired
    public ServicePolicyRelatedServiceImpl(PolicyRelatedMapper policyRelatedMapper) {
        this.policyRelatedMapper = policyRelatedMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getPolicyRelatedListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PolicyRelated> policyRelatedList = policyRelatedMapper.getPolicyRelatedListByPage();
        PageInfo<?> pageInfo = new PageInfo<>(policyRelatedList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<?> insertPolicyRelated(MultipartFile file, PolicyRelated policyRelated) {

        if(file == null){
            log.warn("新增相关政策 上传文件为空。");
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 上传文件并获取携带uuid的文件名
        try {
            String saveFile = FilUploadUtils.saveFile(file);
            policyRelated.setPrFile(saveFile);
        } catch (IOException e) {
            log.error("新增相关政策 文件上传错误 ---> " + e.getMessage());
            return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
        }

        policyRelatedMapper.insert(policyRelated);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> deletePolicyRelatedById(Integer policyRelatedId) {

        // 删除文件
        PolicyRelated policyRelated = policyRelatedMapper.selectByPrimaryKey(policyRelatedId);
        if(!StringUtil.empty(policyRelated.getPrFile())){

            try {
                FilUploadUtils.deleteFile(policyRelated.getPrFile());
            } catch (IOException e) {
                log.error("删除相关政策 文件删除错误 ---> " + e.getMessage());
            }
        }

        policyRelatedMapper.deleteByPrimaryKey(policyRelatedId);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updatePolicyRelatedById(MultipartFile file, PolicyRelated policyRelated) {

        // 判断是否有参数缺失
        if(policyRelated.getPrId() == null){
            log.warn("修改相关政策 相关政策主键缺失。");
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        // 如果修改文件，需先删除原先文件，再上传当前文件
        if(file != null){

            // 删除文件
            PolicyRelated policyRelatedDelete = policyRelatedMapper.selectByPrimaryKey(policyRelated.getPrId());
            if(!StringUtil.empty(policyRelatedDelete.getPrFile())){

                try {
                    FilUploadUtils.deleteFile(policyRelatedDelete.getPrFile());
                } catch (IOException e) {
                    log.error("修改相关政策 文件删除错误 ---> " + e.getMessage());
                }
            }

            // 上传文件并获取携带uuid的文件名
            try {
                String saveFile = FilUploadUtils.saveFile(file);
                policyRelated.setPrFile(saveFile);
            } catch (IOException e) {
                log.error("修改相关政策 文件上传错误 ---> " + e.getMessage());
                return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
            }

        }

        policyRelatedMapper.updateByIdDynamic(policyRelated);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchPolicyRelatedListByPage(Integer pageNum, Integer pageSize, Integer level, String title) {

        PageHelper.startPage(pageNum, pageSize);
        Page<PolicyRelated> policyRelatedList = policyRelatedMapper.searchPolicyRelatedListByPage(level, title);
        PageInfo<?> pageInfo = new PageInfo<>(policyRelatedList);
        return new ResultBean<>(pageInfo);

    }

    @Override
    public ResultBean<List<PolicyRelated>> getAllPolicyRelated() {
        return new ResultBean<>(policyRelatedMapper.selectAll());
    }
}
