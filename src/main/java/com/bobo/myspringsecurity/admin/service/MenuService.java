package com.bobo.myspringsecurity.admin.service;

import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.vo.MenuVo;

import java.util.List;

/**
 * @Description 菜单操作接口
 * @Date 2021/10/8 18:39
 * @Created by bobo
 */
public interface MenuService {
    //三级列表
    List<MenuVo> getTreeList();
    //得到一级分类
    List<MyMenu> getOneLevel();
    List<MyMenu> getNextLevel(Integer parentId,Integer level);
    //插入数据
    boolean addMenu(MyMenu menu);
    //获取菜单数据(通过id)
    MyMenu getMenuInfo(Integer menuId);
    //更新菜单
    boolean updateMenuInfo(MyMenu menu);
    //删除菜单
    boolean deleteMenu(Integer menuId);
    List<MenuIndexDto> getMenu(Integer userId);

}
