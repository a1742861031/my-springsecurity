package com.bobo.myspringsecurity.common.utils;

import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 处理树形菜单的工具类
 * @Date 2021/10/11 16:10
 * @Created by bobo
 */
public class TreeUtil {
    public static List<MenuIndexDto> parseMenuTree(List<MenuIndexDto> list) {
        List<MenuIndexDto> result = new ArrayList<MenuIndexDto>();
        // 1、获取第一级节点
        for (MenuIndexDto menu : list) {
            if (menu.getParentId() == 0) {
                result.add(menu);
            }
        }
        // 2 获取所有一级节点的二级节点
        for (MenuIndexDto parent : result) {
            recursiveTree(parent, list);
        }
        //获取所有二级节点的三级节点
        for (MenuIndexDto menuIndexDto : result) {
            for (MenuIndexDto child : menuIndexDto.getChildren()) {
                recursiveTree(child, list);
            }
        }
        return result;
    }

    public static MenuIndexDto recursiveTree(MenuIndexDto parent, List<MenuIndexDto> list) {
        List<MenuIndexDto> children = new ArrayList<>();
        for (MenuIndexDto menu : list) {
            if (Objects.equals(parent.getId(), menu.getParentId())) {
                children.add(menu);
            }
        }
        parent.setChildren(children);
        return parent;
    }
}
