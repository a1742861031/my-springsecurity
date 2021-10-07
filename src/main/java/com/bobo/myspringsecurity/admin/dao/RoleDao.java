package com.bobo.myspringsecurity.admin.dao;

import com.bobo.myspringsecurity.admin.entity.MyRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select * from my_role where role_id = #{roleId}")
    MyRole getRoleByRoleId(Integer roleId);

    @Select("select * from my_role")
    List<MyRole> getRoleList();

}
