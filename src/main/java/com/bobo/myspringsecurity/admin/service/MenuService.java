package com.bobo.myspringsecurity.admin.service;

import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.vo.MenuVo;

import java.util.List;

/**
 * @Description TODO
 * @Date 2021/10/8 18:39
 * @Created by bobo
 */
public interface MenuService {
    //三级列表
    List<MenuVo> getTreeList();
    //得到一级分类
    List<MyMenu> getOneLevel();
    List<MyMenu> getNextLevel(Integer parentId,Integer level);
}
