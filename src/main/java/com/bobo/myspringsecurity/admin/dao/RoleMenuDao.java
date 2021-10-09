package com.bobo.myspringsecurity.admin.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 角色菜单表
 * @Date 2021/10/8 20:11
 * @Created by bobo
 */
@Mapper
public interface RoleMenuDao {



    //增加角色菜单关联
    @Insert("insert into my_role_menu(role_id,menu_id) values(#{roleId},#{menuId})")
    int addRoleMenu(int roleId, int menuId);

    //根据role_id获取该角色拥有的权限
    @Select("select menu_id from my_role_menu where role_id = #{roleId}")
    List<Integer> getMenuByRoleId(Integer roleId);
    //删除指定角色对应的权限
    @Delete("delete from my_role_menu where role_id = #{roleId} ")
    int deleteByRoleId(Integer roleId);
}
