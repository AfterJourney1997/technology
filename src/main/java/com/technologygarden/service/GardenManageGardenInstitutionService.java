package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.GardenInstitution;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

public interface GardenManageGardenInstitutionService {

    ResultBean<PageInfo<?>> getGardenInstitutionListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertGardenInstitution(MultipartFile file, GardenInstitution gardenInstitution);

    ResultBean<?> deleteGardenInstitutionById(Integer gardenInstitutionId);

    ResultBean<?> updateGardenInstitutionById(MultipartFile file, GardenInstitution gardenInstitution);

    ResultBean<PageInfo<?>> searchGardenInstitutionByPage(Integer pageNum, Integer pageSize, String institutionTitle, Integer kindId);
}
