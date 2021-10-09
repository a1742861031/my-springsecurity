package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.dto.RoleDto;
import com.bobo.myspringsecurity.admin.entity.MyRole;
import com.bobo.myspringsecurity.admin.service.RoleService;
import com.bobo.myspringsecurity.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: role控制器
 * @author: bobo
 * @create: 2021-10-07 17:55
 **/
@RestController
@ApiOperation("角色相关操作")
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

//    @GetMapping("{roleId}")
//    @ApiOperation(value = "根据角色id获取该角色信息")
//    public Result<MyRole> getRoleByRoleId(@PathVariable Integer roleId) {
//        MyRole role = roleService.getRoleByRoleId(roleId);
//        return Result.ok().data("role", role);
//    }

    @GetMapping("")
    @ApiOperation(value = "获取角色列表")
    public Result<MyRole> getRoleList(String roleName) {
        List<MyRole> roleList = roleService.getRoleList(roleName);
        return Result.ok().data("roleList", roleList);
    }

    @PostMapping()
    @ApiOperation(value = "新增角色")
    public Result<Object> addRole(@RequestBody RoleDto roleDto) {
        boolean isSuccess = roleService.addRole(roleDto);
        if (isSuccess)
            return Result.ok().message("新增角色成功");
        else {
            return Result.error().message("新增角色失败");
        }
    }

    @GetMapping("{roleId}")
    @ApiOperation(value = "根据id查询角色")
    public Result<RoleDto> getRoleById(@PathVariable Integer roleId) {
        RoleDto role = roleService.getRoleById(roleId);
        return Result.ok().data("role", role);
    }

    //修改角色信息
    @PutMapping("")
    @ApiOperation(value = "修改角色信息")
    public Result<RoleDto> updateRole(@RequestBody RoleDto roleDto) {
        boolean isSuccess = roleService.editRole(roleDto);
        if (isSuccess)
            return Result.ok().message("修改角色成功");
        else {
            return Result.error().message("修改角色失败");
        }
    }

    //根据角色id删除角色信息
    @DeleteMapping("{roleId}")
    @ApiOperation(value = "根据id删除角色信息")
    public Result<Object> deleteRoleById(@PathVariable Integer roleId) {
        boolean isSuccess = roleService.deleteRole(roleId);
        if (isSuccess)
            return Result.ok().message("删除角色成功");
        else {
            return Result.error().message("删除角色失败");
        }
    }
}
