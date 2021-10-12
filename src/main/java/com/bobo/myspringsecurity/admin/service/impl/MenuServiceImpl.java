package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.MenuDao;
import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.service.MenuService;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import com.bobo.myspringsecurity.common.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Date 2021/10/8 18:39
 * @Created by bobo
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuVo> getTreeList() {
        return menuDao.getNodeTree();
    }

    @Override
    public List<MyMenu> getOneLevel() {
        return menuDao.getOneLevelMenu();
    }

    @Override
    public List<MyMenu> getNextLevel(Integer parentId,Integer level) {
        if (level == 2) {
            return menuDao.getTwoLevelMenu(parentId);
        } else {
            List<MyMenu> threeLevelMenu = menuDao.getThreeLevelMenu(parentId);
            for (MyMenu levelMenu : threeLevelMenu) {
                levelMenu.setHasChildren(false);
            }
            return threeLevelMenu;
        }
    }

    @Override
    public boolean addMenu(MyMenu menu) {

        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        int i = menuDao.addMenu(menu);
        return i > 0;
    }

    @Override
    public MyMenu getMenuInfo(Integer menuId) {
        return menuDao.getMenuInfoById(menuId);
    }

    @Override
    public boolean updateMenuInfo(MyMenu menu) {
        int i = menuDao.updateMenu(menu);
        return i > 0;
    }

    @Override
    @Transactional
    public boolean deleteMenu(Integer menuId) {
        int i = menuDao.deleteMenu(menuId);
        //得到一级下所有二级
        List<MyMenu> twoLevelMenus = menuDao.getTwoLevelMenu(menuId);
        for (MyMenu twoLevelMenu : twoLevelMenus) {
            menuDao.deleteMenu(twoLevelMenu.getMenuId());
            List<MyMenu> threeLevelMenus = menuDao.getThreeLevelMenu(twoLevelMenu.getMenuId());
            for (MyMenu threeLevelMenu : threeLevelMenus) {
                menuDao.deleteMenu(threeLevelMenu.getMenuId());
            }
        }
        return true;
    }

    @Override
    public List<MenuIndexDto> getMenu(Integer userId) {
        List<MenuIndexDto> list = menuDao.listByUserId(userId);
        List<MenuIndexDto> result = TreeUtil.parseMenuTree(list);
        return result;
    }

}
