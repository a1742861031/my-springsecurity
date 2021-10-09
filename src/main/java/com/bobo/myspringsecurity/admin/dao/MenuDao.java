package com.bobo.myspringsecurity.admin.dao;

import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description Mapper表dao层
 * @Date 2021/10/8 12:54
 * @Created by bobo
 */
@Mapper
public interface MenuDao {
    //得到树形菜单
    List<MenuVo> getNodeTree();
    //得到所有的一级菜单
    @Select("SELECT * FROM my_menu WHERE type = 0 ORDER BY sort ")
    List<MyMenu> getOneLevelMenu();
    //得到该一级菜单下所有的二级菜单
    @Select("SELECT * FROM my_menu WHERE type = 1 AND parent_id = #{parentId} ORDER BY sort")
    List<MyMenu> getTwoLevelMenu(Integer parentId);
    //得到该二级分类下所有的三级分类菜单
    @Select("SELECT * FROM my_menu WHERE type = 2 AND parent_id = #{parentId} ORDER BY sort")
    List<MyMenu> getThreeLevelMenu(Integer parentId);
}
