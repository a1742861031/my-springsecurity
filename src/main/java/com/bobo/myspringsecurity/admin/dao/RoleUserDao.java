package com.bobo.myspringsecurity.admin.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleUserDao {
    //根据用户id查询角色id
    @Select("select role_id from my_role_user where user_id = #{userId}")
    Integer getRoleIdByUserId(String userId);

    @Update("update my_role_user set role_id = #{roleId} where user_id   = #{userId}")
    Integer updateRoleUser(Integer roleId, Integer userId);

    @Insert("insert into my_role_user(roleId,userId) values(#{roleId},#{userId})")
    Integer insertRoleUser(Integer roleId,Integer userId);
}
