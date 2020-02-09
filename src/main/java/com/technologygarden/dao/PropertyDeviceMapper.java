package com.technologygarden.dao;

import com.github.pagehelper.Page;
import com.technologygarden.entity.PropertyDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PropertyDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyDevice record);

    PropertyDevice selectByPrimaryKey(Integer id);

    List<PropertyDevice> selectAll();

    int updateByPrimaryKey(PropertyDevice record);

    Page<PropertyDevice> selectSystemPropertyDeviceListByPage(@Param("categoryId") Integer categoryId);

    int insertSystemPropertyDeviceDynamic(PropertyDevice propertyDevice);

    int deleteSystemPropertyDeviceByCategoryId(Integer id);

    int updateSystemPropertyDeviceByIdDynamic(PropertyDevice propertyDevice);

    int updateSystemPropertyDeviceByCategoryId(PropertyDevice propertyDevice);

    Page<PropertyDevice> searchSystemPropertyDeviceByPage(@Param("categoryId") Integer categoryId, @Param("categoryName") String categoryName, @Param("propertyName") String propertyName);

    List<PropertyDevice> selectCateGoryByKind(@Param("kind") Integer kind);

    List<PropertyDevice> selectPropertyByCategoryId(@Param("categoryId") Integer categoryId);

    List<PropertyDevice> selectPropertyDeviceWithDevice();

    List<PropertyDevice> selectDeviceCategoryById(@Param("propertyDeviceId") Integer propertyDeviceId);

    Page<PropertyDevice> selectFatherSystemPropertyDeviceByKind(@Param("kind") Integer kind);

    Page<PropertyDevice> searchFatherSystemPropertyDeviceByPage(@Param("categoryName") String categoryName);
}