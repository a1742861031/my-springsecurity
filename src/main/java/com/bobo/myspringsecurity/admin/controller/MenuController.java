package com.bobo.myspringsecurity.admin.controller;

import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.service.MenuService;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import com.bobo.myspringsecurity.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
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
}
