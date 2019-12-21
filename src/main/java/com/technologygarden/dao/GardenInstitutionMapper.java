package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.GardenInstitution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GardenInstitutionMapper {
    int deleteByPrimaryKey(Integer giId);

    int insert(GardenInstitution record);

    GardenInstitution selectByPrimaryKey(Integer giId);

    List<GardenInstitution> selectAll();

    int updateByPrimaryKey(GardenInstitution record);

    Page<GardenInstitution> selectAllByPage();

    int updateByIdDynamic(GardenInstitution gardenInstitution);

    Page<GardenInstitution> searchGardenInstitutionByPage(@Param("institutionTitle") String institutionTitle, @Param("kindId") Integer kindId);
}