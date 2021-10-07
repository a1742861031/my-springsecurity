package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.entity.MyRole;
import com.bobo.myspringsecurity.admin.service.RoleService;
import com.bobo.myspringsecurity.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{roleId}")
    @ApiOperation(value = "根据角色id获取该角色信息")
    public Result<MyRole> getRoleByRoleId(@PathVariable Integer roleId) {
        MyRole role = roleService.getRoleByRoleId(roleId);
        return Result.ok().data("role", role);
    }
    @GetMapping
    @ApiOperation(value = "获取角色列表")
    public Result<MyRole> getRoleList(){
        List<MyRole> roleList = roleService.getRoleList();
        return Result.ok().data("roleList",roleList);
    }

}
