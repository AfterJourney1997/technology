package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/asset/assetCountForm")
@Api(tags = "资产管理 / 资产统计报表接口", value = "AssetAssetCountFormController")
public class AssetAssetCountFormController {

    private final AssetAssetCountFormService assetAssetCountFormService;

    @Autowired
    public AssetAssetCountFormController(AssetAssetCountFormService assetAssetCountFormService) {
        this.assetAssetCountFormService = assetAssetCountFormService;
    }

    @GetMapping("/category")
    @ApiOperation(value = "获取类别的资产统计报表", notes = "参数包括：无")
    public ResultBean<?> getAssetCountCategoryFormJSON(){

        JSONObject categoryFormJSON = assetAssetCountFormService.getAssetCountCategoryFormJSON();
        return new ResultBean<>(categoryFormJSON);

    }

}
