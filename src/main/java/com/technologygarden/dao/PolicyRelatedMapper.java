package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PolicyRelated;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PolicyRelatedMapper {
    int deleteByPrimaryKey(Integer prId);

    int insert(PolicyRelated record);

    PolicyRelated selectByPrimaryKey(Integer prId);

    List<PolicyRelated> selectAll();

    int updateByPrimaryKey(PolicyRelated record);

    Page<PolicyRelated> getPolicyRelatedListByPage();

    int updateByIdDynamic(PolicyRelated policyRelated);

    Page<PolicyRelated> searchPolicyRelatedListByPage(@Param("level") Integer level, @Param("title") String title);
}