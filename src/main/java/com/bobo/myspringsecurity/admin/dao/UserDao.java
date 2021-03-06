package com.bobo.myspringsecurity.admin.dao;

import com.bobo.myspringsecurity.admin.dto.MyUserDto;
import com.bobo.myspringsecurity.admin.entity.MyUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    //分页查询所有的用户 这里写一个sql语句即可 后面使用分页插件
    List<MyUser> getUserList(MyUserDto myUserDto);
    //通过id查询用户
    @Select("select u.user_id,u.dept_id,u.user_name,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time from my_user u where u.user_id = #{userId}")
    MyUser getUserById(String id);
    //更新用户
    int updateUser(MyUser myUser);
    //新增用户
    int insert(MyUser myUser);
    //删除用户
    @Delete("delete from my_user where user_id = #{userId}")
    int deleteUser(Integer userId);
    //根据用户名获取用户
    @Select("select * from my_user where user_name = #{userName}")
    MyUser getUser(String userName);
}
