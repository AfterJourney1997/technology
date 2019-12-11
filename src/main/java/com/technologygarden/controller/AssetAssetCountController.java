package com.technologygarden.controller;

import com.github.pagehelper.Page;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/asset")
@Api(tags = "资产管理 / 资产统计接口", value = "AssetAssetCountController")
public class AssetAssetCountController {

    private final AssetAssetCountService assetAssetCountService;

    @Autowired
    public AssetAssetCountController(AssetAssetCountService assetAssetCountService) {
        this.assetAssetCountService = assetAssetCountService;
    }

    @RequestMapping(value = "/assetCount", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取资产统计列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<Page<CompanyRoomDevice>> getAssetCountByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetAssetCountService.getAssetCountByPage(pageNum, pageSize);

    }
}
