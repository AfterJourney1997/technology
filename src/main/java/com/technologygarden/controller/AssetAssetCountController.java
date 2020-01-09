package com.technologygarden.controller;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.CompanyRoomDevice;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.AssetAssetCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/asset/assetCount")
@Api(tags = "资产管理 / 资产统计接口", value = "AssetAssetCountController")
public class AssetAssetCountController {

    private final AssetAssetCountService assetAssetCountService;

    @Autowired
    public AssetAssetCountController(AssetAssetCountService assetAssetCountService) {
        this.assetAssetCountService = assetAssetCountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取资产统计列表", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<PageInfo<?>> getAssetCountByPage(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return assetAssetCountService.getAssetCountByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "回收资产", notes = "参数包括：资产分配对象，只需资产分配id crdId，设备id crdDeviceId，设备分配数量 crdNumber")
    public ResultBean<?> deleteAssetCount(@RequestBody CompanyRoomDevice companyRoomDevice){

        return assetAssetCountService.deleteAssetCount(companyRoomDevice);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索资产统计列表", notes = "参数包括：页数，每页数量，企业名称，房间名称，类别id")
    public ResultBean<PageInfo<?>> searchAssetCountByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String companyName, String roomName, Integer categoryId){

        return assetAssetCountService.searchAssetCountByPage(pageNum, pageSize, companyName, roomName, categoryId);

    }




}
