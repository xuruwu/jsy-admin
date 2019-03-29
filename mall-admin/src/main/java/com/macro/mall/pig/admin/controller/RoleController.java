package com.macro.mall.pig.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.macro.mall.domain.CommonResult;
import com.macro.mall.pig.admin.model.dto.RoleDTO;
import com.macro.mall.pig.admin.model.entity.SysRole;
import com.macro.mall.pig.admin.service.SysRoleMenuService;
import com.macro.mall.pig.admin.service.SysRoleService;
import com.macro.mall.pig.common.constant.CommonConstant;
import com.macro.mall.pig.common.util.Query;
import com.macro.mall.pig.common.util.R;
import com.macro.mall.pig.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author lengleng
 * @date 2017/11/5
 */
@Api(tags = "后台账户权限-角色管理", description = "角色管理:RoleController",position = 100)
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @ApiOperation(value = "通过ID查询角色信息")
    @ApiResponses({@ApiResponse(code = 201, response = SysRole.class,
            message="状态200的data格式说明：data返回值为对象")})
    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult role(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.selectById(id);
        //return  sysRole;
         return new CommonResult().success(sysRole);
    }

    /**
     * 添加角色
     *
     * @param roleDto 角色信息
     * @return success、false
     */
    @ApiOperation(value = "添加角色")
    @ApiResponses({@ApiResponse(code = 201, response = Boolean.class,
            message="状态200的data格式说明：data返回值为对象")})
    @PostMapping
    @ResponseBody
    public CommonResult role(@RequestBody RoleDTO roleDto) {
        // TODO 待确认 Boolean 返回值是否需要真假 再返回前端页面
        Boolean b = sysRoleService.insertRole(roleDto);
        return new CommonResult().success(b);
    }

    /**
     * 修改角色
     *
     * @param roleDto 角色信息
     * @return success/false
     */
    @ApiOperation(value = "添加角色")
    @ApiResponses({@ApiResponse(code = 201, response = Boolean.class,
            message="状态200的data格式说明：data返回值为对象")})
    @PutMapping
    @ResponseBody
    public CommonResult roleUpdate(@RequestBody RoleDTO roleDto) {
        Boolean b = sysRoleService.updateRoleById(roleDto);
        return new CommonResult().success(b);
    }

    @ApiOperation(value = "删除角色")
    @ApiResponses({@ApiResponse(code = 201, response = Boolean.class,
            message="状态200的data格式说明：data返回值为对象")})
    @DeleteMapping("/{id}")
    @ResponseBody
    public CommonResult roleDel(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.selectById(id);
        sysRole.setDelFlag(CommonConstant.STATUS_DEL);
        Boolean b = sysRoleService.updateById(sysRole);
        return new CommonResult().success(b);
    }

    /**
     * 获取角色列表
     *
     * @param deptId 部门ID
     * @return 角色列表
     */
    @ApiOperation(value = "获取角色列表")
    @ApiResponses({@ApiResponse(code = 201, response = SysRole.class,
            message="状态200的data格式说明：data返回值为对象")})
    @GetMapping("/roleList/{deptId}")
    @ResponseBody
    public CommonResult  roleList(@PathVariable Integer deptId) {
        return  new CommonResult().success(sysRoleService.selectListByDeptId(deptId)) ;
    }

    /**
     * 分页查询角色信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @ApiOperation("分页查询角色信息")
    @ApiResponses({@ApiResponse(code = 201, response = Page.class,
            message="状态200的data格式说明：data返回值为分页数据")})
    @RequestMapping("/rolePage")
    @ResponseBody
    public CommonResult rolePage(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return new CommonResult().success(sysRoleService.selectwithDeptPage(new Query<>(params), new EntityWrapper<>()));
    }

    /**
     * 更新角色菜单
     *
     * @param roleId  角色ID
     * @param menuIds 菜单结合
     * @return success、false
     */
    @ApiOperation("更新角色菜单")
    @ApiResponses({@ApiResponse(code = 201, response = R.class,
            message="状态200的data格式说明：data返回值为对象")})
    @PutMapping("/roleMenuUpd")
    @ResponseBody
    public CommonResult roleMenuUpd(Integer roleId, @RequestParam(value = "menuIds", required = false) String menuIds) {
        SysRole sysRole = sysRoleService.selectById(roleId);
        Boolean b= sysRoleMenuService.insertRoleMenus(sysRole.getRoleCode(), roleId, menuIds);
        R<Boolean> rb =new  R<Boolean>(b);
        return  new CommonResult().success(rb);
    }
}
