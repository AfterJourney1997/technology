package com.technologygarden.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.technologygarden.dao.PropertyDeviceMapper;
import com.technologygarden.entity.Device;
import com.technologygarden.entity.PropertyDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("assetAssetCountFormService")
public class AssetAssetCountFormServiceImpl implements AssetAssetCountFormService {

    private final PropertyDeviceMapper propertyDeviceMapper;

    @Autowired
    public AssetAssetCountFormServiceImpl(PropertyDeviceMapper propertyDeviceMapper) {
        this.propertyDeviceMapper = propertyDeviceMapper;
    }

    @Override
    public JSONObject getAssetCountCategoryFormJSON() {

        List<String> category;
        List<Integer> total = new ArrayList<>();
        List<Integer> remain = new ArrayList<>();

        List<PropertyDevice> propertyDeviceList = propertyDeviceMapper.selectPropertyDeviceWithDevice();

        // 收集类别名称
        category = propertyDeviceList
                .stream()
                .map(PropertyDevice::getCategoryName)
                .collect(Collectors.toList());

        // 收集类别总数和剩余数量
        for(PropertyDevice propertyDevice : propertyDeviceList){

            total.add(propertyDevice.getDeviceList()
                    .stream()
                    .map(Device::getTotal)
                    .reduce(Integer::sum)
                    .get());

            remain.add(propertyDevice.getDeviceList()
                    .stream()
                    .map(Device::getRemain)
                    .reduce(Integer::sum)
                    .get());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", category);
        jsonObject.put("total", total);
        jsonObject.put("remain", remain);

        return jsonObject;
    }
}
