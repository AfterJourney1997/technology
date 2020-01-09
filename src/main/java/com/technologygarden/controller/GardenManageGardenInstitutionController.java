package com.technologygarden.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.GardenInstitution;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.GardenManageGardenInstitutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(value = "/gardenManage/institution")
@Api(tags = "园区管理 / 园区制度接口", value = "GardenManageGardenInstitutionController")
public class GardenManageGardenInstitutionController {

    private final GardenManageGardenInstitutionService gardenManageGardenInstitutionService;

    @Autowired
    public GardenManageGardenInstitutionController(GardenManageGardenInstitutionService gardenManageGardenInstitutionService) {
        this.gardenManageGardenInstitutionService = gardenManageGardenInstitutionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取园区制度", notes = "参数包括：页数，每页数量，均必填（kind 1为园区，2为企业）")
    public ResultBean<PageInfo<?>> getGardenInstitutionListByPage(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        return gardenManageGardenInstitutionService.getGardenInstitutionListByPage(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增园区制度", notes = "参数包括：文件，园区制度对象json")
    public ResultBean<?> insertGardenInstitution(MultipartFile file, String gardenInstitution) {

        GardenInstitution gardenInstitutionObject = JSONObject.parseObject(gardenInstitution, GardenInstitution.class);
        return gardenManageGardenInstitutionService.insertGardenInstitution(file, gardenInstitutionObject);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除园区制度", notes = "参数包括：页数，每页数量，均必填")
    public ResultBean<?> deleteGardenInstitutionById(Integer gardenInstitutionId) {

        return gardenManageGardenInstitutionService.deleteGardenInstitutionById(gardenInstitutionId);

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改园区制度", notes = "参数包括：文件，园区制度对象json")
    public ResultBean<?> updateGardenInstitutionById(MultipartFile file, String gardenInstitution) {

        GardenInstitution gardenInstitutionObject = JSONObject.parseObject(gardenInstitution, GardenInstitution.class);
        return gardenManageGardenInstitutionService.updateGardenInstitutionById(file, gardenInstitutionObject);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索园区制度", notes = "参数包括：页数，每页数量，制度标题，制度类别id")
    public ResultBean<PageInfo<?>> searchGardenInstitutionByPage(@NonNull Integer pageNum, @NonNull Integer pageSize, String institutionTitle, Integer kindId) {

        return gardenManageGardenInstitutionService.searchGardenInstitutionByPage(pageNum, pageSize, institutionTitle, kindId);

    }

}
