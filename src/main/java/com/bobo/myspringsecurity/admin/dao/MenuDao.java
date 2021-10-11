package com.bobo.myspringsecurity.admin.dao;

import com.bobo.myspringsecurity.admin.dto.MenuIndexDto;
import com.bobo.myspringsecurity.admin.entity.MyMenu;
import com.bobo.myspringsecurity.admin.vo.MenuVo;
import org.apache.ibatis.annotations.*;

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

    //新增菜单
    int addMenu(MyMenu myMenu);

    //通过菜单id获取菜单信息
    @Select("select * from my_menu where menu_id = #{menuId}")
    MyMenu getMenuInfoById(Integer menuId);

    //提交修改菜单
    int updateMenu(MyMenu myMenu);

    //删除菜单
    @Delete("delete from my_menu where menu_id = #{menuId}")
    int deleteMenu(Integer menuId);

    @Select("SELECT DISTINCT sp.menu_id,sp.parent_id,sp.menu_name,sp.icon,sp.url,sp.type,sp.permission  " +
            "FROM my_role_user sru " +
            "INNER JOIN my_role_menu srp ON srp.role_id = sru.role_id " +
            "LEFT JOIN my_menu sp ON srp.menu_id = sp.menu_id " +
            "WHERE " +
            "sru.user_id = #{userId}")
    @Result(property = "title", column = "menu_name")
    @Result(property = "href", column = "url")
    @Result(property = "id",column = "menu_id")
    List<MenuIndexDto> listByUserId(@Param("userId") Integer userId);


}
