package com.macro.mall.controller;

import com.macro.mall.domain.CommonResult;
import com.macro.mall.dto.UmsPermissionNode;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台用户权限管理
 * Created by macro on 2018/9/29.
 */
@Api(tags = "后台账户权限-后台用户权限管理(已弃用)", description = "后台用户权限管理:UmsPermissionController",position = 6)
@ApiIgnore
@Controller
@RequestMapping("/permission")
@Deprecated
public class UmsPermissionController {
    @Autowired
    private UmsPermissionService permissionService;
    @ApiOperation("添加权限")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody UmsPermission permission) {
        int count = permissionService.create(permission);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id,permission);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    @ApiOperation("获取权限详情")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable Long id) {
        UmsPermission umsPermission = permissionService.get(id);
        return new CommonResult().success(umsPermission);
    }

    @ApiOperation("根据id批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public Object treeList() {
        List<UmsPermissionNode> permissionNodeList = permissionService.treeList();
        return new CommonResult().success(permissionNodeList);
    }

    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        List<UmsPermission> permissionList = permissionService.list();
        return new CommonResult().success(permissionList);
    }
}
