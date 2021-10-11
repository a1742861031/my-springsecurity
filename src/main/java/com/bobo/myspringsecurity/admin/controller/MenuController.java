package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.service.MenuService;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import com.bobo.myspringsecurity.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 菜单控制器
 * @Date 2021/10/8 18:36
 * @Created by bobo
 */
@RestController
@ApiOperation("菜单相关操作")
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/tree")
    @ApiOperation(value = "获取三级菜单")
    public Result<MenuVo> getTree() {
        List<MenuVo> treeList = menuService.getTreeList();
        return Result.ok().message("获取菜单列表成功").data("tree", treeList);
    }

    @GetMapping(value = {"/{level}", "/{level}/{parentId}"})
    @ApiOperation("获取一级二级或三级菜单")
    public Result<MyMenu> getLevelMenu(@PathVariable Integer level, @PathVariable(required = false) Integer parentId) {
        List<MyMenu> result;
        if (level == 1) {
            result = menuService.getOneLevel();
        } else if (level == 2 || level == 3) {
            result = menuService.getNextLevel(parentId, level);
        } else {
            return Result.error().message("请传入正确的参数");
        }
        return Result.ok().data("result", result);
    }

    //添加菜单
    @PostMapping()
    @ApiOperation("添加菜单")
    public Result<Object> addMenu(@RequestBody MyMenu menu) {
        boolean isSuccess = menuService.addMenu(menu);
        if (isSuccess) {
            return Result.ok().message("新增菜单成功");
        } else {
            return Result.error().message("新增菜单失败");
        }
    }

    //获取菜单信息
    @GetMapping("/menuInfo/{menuId}")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @ApiOperation("获取菜单信息")
    public Result<Object> getMenuInfo(@PathVariable Integer menuId) {
        MyMenu menu = menuService.getMenuInfo(menuId);
        return Result.ok().data("menu", menu);
    }

    //更新菜单信息
    @PutMapping()
    @ApiOperation("修改菜单信息")
    public Result<Object> updateMenu(@RequestBody MyMenu menu) {
        boolean isSuccess = menuService.updateMenuInfo(menu);
        if (isSuccess) {
            return Result.ok().message("更新菜单成功");
        } else {
            return Result.error().message("更新菜单失败");
        }
    }

    @DeleteMapping("{menuId}")
    @ApiOperation("删除菜单信息")
    public Result<String> deleteMenu(@PathVariable Integer menuId) {
        boolean isSuccess = menuService.deleteMenu(menuId);

        if (isSuccess) {
            return Result.ok().message("删除菜单成功");
        } else {
            return Result.error().message("删除菜单失败");
        }
    }
    @GetMapping(value = "/index/{userId}")
    @ResponseBody
    @ApiOperation(value = "通过用户id获取菜单")
    public List<MenuIndexDto> getMenu(@PathVariable  Integer userId) {
        return menuService.getMenu(userId);
    }

}
