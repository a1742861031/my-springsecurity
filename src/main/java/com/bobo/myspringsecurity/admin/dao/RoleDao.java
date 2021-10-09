package com.bobo.myspringsecurity.admin.dao;

import com.bobo.myspringsecurity.admin.dto.RoleDto;
import com.bobo.myspringsecurity.admin.entity.MyRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select * from my_role where role_id = #{roleId}")
    MyRole getRoleByRoleId(Integer roleId);

    List<MyRole> getRoleList(@Nullable String roleName);

    //新增角色
    int addRole(RoleDto roleDto);

    //获取角色
    @Select("select * from my_role where role_id = #{roleId}")
    MyRole getRoleById(Integer id);

    //修改角色
    @Update("update my_role set role_name = #{roleName},description = #{description},update_time=now() where role_id = #{roleId}")
    int updateRole(MyRole role);
    //删除角色
    @Delete("delete from my_role where role_id = #{roleId}")
    int deleteRole(Integer roleId);
}
