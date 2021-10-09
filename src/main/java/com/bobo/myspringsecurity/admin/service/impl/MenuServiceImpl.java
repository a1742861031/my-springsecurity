package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.MenuDao;
import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.service.MenuService;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(level == 2){
            return menuDao.getTwoLevelMenu(parentId);
        }
        else {
            List<MyMenu> threeLevelMenu = menuDao.getThreeLevelMenu(parentId);
            for (MyMenu levelMenu : threeLevelMenu) {
                levelMenu.setHasChildren(false);
            }
            return threeLevelMenu;
        }
    }
}
