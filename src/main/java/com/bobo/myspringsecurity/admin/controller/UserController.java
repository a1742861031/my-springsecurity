package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.dto.MyUserDto;
import com.bobo.myspringsecurity.admin.entity.MyUser;
import com.bobo.myspringsecurity.admin.service.UserService;
import com.bobo.myspringsecurity.common.utils.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: User控制器类
 * @author: bobo
 * @create: 2021-10-07 13:01
 **/
@RestController
@ApiOperation("用户相关操作")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/{pageNum}/{pageSize}")
    @ApiOperation(value = "用户列表")
    public Result<MyUser> index(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody(required = false) MyUserDto dto) {
        PageInfo<MyUser> allUsersByPage = userService.getAllUsersByPage(pageNum, pageSize, dto);
        return Result.ok().data("data", allUsersByPage);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取用户信息")
    public Result<MyUser> getUserById(@PathVariable String id) {
        MyUser user = userService.getUserById(id);
        return Result.ok().data("item", user);
    }

    @PutMapping()
    @ApiOperation(value = "修改用户")
    public Result<Object> updateUser(@RequestBody MyUser user) {
        boolean issuccess = userService.updateUser(user);
        if (issuccess) {
            return Result.ok().message("更新用户成功");
        } else {
            return Result.error().message("更新用户失败");
        }
    }

    @PostMapping()
    @ApiOperation(value = "新增用户")
    public Result<Object> addUser(@RequestBody MyUser user) {
        boolean isSuccess = userService.addUser(user);
        if (isSuccess) {
            return Result.ok().message("新增用户成功");
        } else {
            return Result.error().message("新增用户失败");
        }
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "删除用户")
    public Result<Object> deleteUser(@PathVariable Integer userId) {
        boolean isSuccess = userService.deleteUser(userId);
        if (isSuccess) {
            return Result.ok().message("删除用户成功");
        }
        return Result.error().message("删除用户失败");
    }
}
