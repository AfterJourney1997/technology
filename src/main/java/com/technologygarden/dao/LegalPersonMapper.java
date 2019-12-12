package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.LegalPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LegalPersonMapper {

    int deleteByPrimaryKey(Integer lpId);

    int insert(LegalPerson record);

    LegalPerson selectByPrimaryKey(Integer lpId);

    List<LegalPerson> selectAll();

    int updateByPrimaryKey(LegalPerson record);

    Page<LegalPerson> selectLegalPersonByPage();

    List<LegalPerson> selectLegalPersonByDegreeId(Integer degreeId);

    List<LegalPerson> selectLegalPersonByJobTitleId(Integer jobTitleId);

    int insertReturnPrimaryKey(LegalPerson legalPerson);

    Page<LegalPerson> searchLegalPersonByPage(@Param("legalPersonName") String legalPersonName, @Param("companyName") String companyName);
}