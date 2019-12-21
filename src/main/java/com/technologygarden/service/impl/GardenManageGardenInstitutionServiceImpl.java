package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.GardenInstitutionMapper;
import com.technologygarden.entity.GardenInstitution;
import com.technologygarden.entity.PolicyRelated;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.service.GardenManageGardenInstitutionService;
import com.technologygarden.util.FilUploadUtils;
import com.technologygarden.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service("gardenManageGardenInstitutionService")
public class GardenManageGardenInstitutionServiceImpl implements GardenManageGardenInstitutionService {

    private final GardenInstitutionMapper gardenInstitutionMapper;

    @Autowired
    public GardenManageGardenInstitutionServiceImpl(GardenInstitutionMapper gardenInstitutionMapper) {
        this.gardenInstitutionMapper = gardenInstitutionMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getGardenInstitutionListByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<GardenInstitution> gardenInstitutionList = gardenInstitutionMapper.selectAllByPage();
        PageInfo<?> pageInfo = new PageInfo<>(gardenInstitutionList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertGardenInstitution(MultipartFile file, GardenInstitution gardenInstitution) {

        if(file == null){
            log.warn("新增园区制度 上传文件为空。");
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }

        // 上传文件并获取携带uuid的文件名
        try {
            String saveFile = FilUploadUtils.saveFile(file);
            gardenInstitution.setGiFile(saveFile);
        } catch (IOException e) {
            log.error("新增园区制度 文件上传错误 ---> " + e.getMessage());
            return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
        }

        gardenInstitutionMapper.insert(gardenInstitution);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<?> deleteGardenInstitutionById(Integer gardenInstitutionId) {

        // 删除文件
        GardenInstitution gardenInstitution = gardenInstitutionMapper.selectByPrimaryKey(gardenInstitutionId);
        if(!StringUtil.empty(gardenInstitution.getGiFile())){

            try {
                FilUploadUtils.deleteFile(gardenInstitution.getGiFile());
            } catch (IOException e) {
                log.error("删除园区制度 文件删除错误 ---> " + e.getMessage());
            }
        }

        gardenInstitutionMapper.deleteByPrimaryKey(gardenInstitutionId);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<?> updateGardenInstitutionById(MultipartFile file, GardenInstitution gardenInstitution) {

        // 判断是否有参数缺失
        if(gardenInstitution.getGiId() == null){
            log.warn("修改园区制度 园区制度主键缺失。");
            return new ResultBean<>(ResultStatus.PARAMETER_MISSING_ERROR);
        }

        // 如果修改文件，需先删除原先文件，再上传当前文件
        if(file != null){

            // 删除文件
            GardenInstitution gardenInstitutionDelete = gardenInstitutionMapper.selectByPrimaryKey(gardenInstitution.getGiId());
            if(!StringUtil.empty(gardenInstitutionDelete.getGiFile())){

                try {
                    FilUploadUtils.deleteFile(gardenInstitutionDelete.getGiFile());
                } catch (IOException e) {
                    log.error("修改园区制度 文件删除错误 ---> " + e.getMessage());
                }
            }

            // 上传文件并获取携带uuid的文件名
            try {
                String saveFile = FilUploadUtils.saveFile(file);
                gardenInstitution.setGiFile(saveFile);
            } catch (IOException e) {
                log.error("修改园区制度 文件上传错误 ---> " + e.getMessage());
                return new ResultBean<>(ResultStatus.FILE_UPLOAD_ERROR);
            }

        }

        gardenInstitutionMapper.updateByIdDynamic(gardenInstitution);
        return new ResultBean<>();

    }

    @Override
    public ResultBean<PageInfo<?>> searchGardenInstitutionByPage(Integer pageNum, Integer pageSize, String institutionTitle, Integer kindId) {

        PageHelper.startPage(pageNum, pageSize);
        Page<GardenInstitution> gardenInstitutionList = gardenInstitutionMapper.searchGardenInstitutionByPage(institutionTitle, kindId);
        PageInfo<?> pageInfo = new PageInfo<>(gardenInstitutionList);
        return new ResultBean<>(pageInfo);

    }
}
