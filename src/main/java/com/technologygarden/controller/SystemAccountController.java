package com.technologygarden.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.ResultBean.ResultStatus;
import com.technologygarden.entity.Role;
import com.technologygarden.service.SystemAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/system/account")
@Api(tags = "系统配置 / 账号管理接口", value = "SystemRightsController")
public class SystemAccountController {

    private final SystemAccountService systemAccountService;

    @Autowired
    public SystemAccountController(SystemAccountService systemAccountService) {
        this.systemAccountService = systemAccountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部管理员账号列表", notes = "参数包括：页数、每页数量")
    public ResultBean<?> getAllAccount(@NonNull Integer pageNum, @NonNull Integer pageSize){

        return systemAccountService.getAllAdmin(pageNum, pageSize);

    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增管理员账号，密码默认为123456", notes = "参数包括：管理员账号")
    public ResultBean<?> insertAdmin(@NonNull String account){

        return systemAccountService.insertAdmin(account);

    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除管理员账号", notes = "参数包括：管理员账号主键id")
    public ResultBean<?> deleteAdmin(@NonNull Integer id){

        return systemAccountService.deleteAdmin(id);

    }

    @RequestMapping(value = "/rights", method = RequestMethod.GET)
    @ApiOperation(value = "获取管理员拥有的权限列表", notes = "参数包括：账号id（不填则默认查询全部权限）")
    public ResultBean<?> getAllMenuWithRights(Integer roleId){

        return systemAccountService.getAllMenuWithRightsByRoleId(roleId);

    }

    @RequestMapping(value = "/rights", method = RequestMethod.POST)
    @ApiOperation(value = "分配权限到指定账号", notes = "参数包括：账号主键 id，权限idList rightsList，以JSON传过来即可")
    public ResultBean<?> distributeRightsToAccount(@RequestBody String JSONString){

        JSONObject jsonObject = JSONObject.parseObject(JSONString);
        Integer id = jsonObject.getInteger("id");
        JSONArray JSONRightsList = jsonObject.getJSONArray("rightsList");
        List<Integer> rightsList = JSONObject.parseArray(JSONRightsList.toJSONString(), Integer.class);

        // 判断参数是否缺失
        if (id == null || rightsList == null){

            log.warn("分配权限 参数缺失 ---> [{}]", JSONString);
            return new ResultBean<>(ResultStatus.PARAMETER_ERROR);
        }
        return systemAccountService.distributeRightsToAccount(id, rightsList);

    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ApiOperation(value = "修改管理员账号的密码", notes = "参数包括：账号主键 id、新密码")
    public ResultBean<?> updatePassword(@NonNull Integer id, @NonNull String newPassword){

        return systemAccountService.updateAdminPassword(id, newPassword);

    }


}
