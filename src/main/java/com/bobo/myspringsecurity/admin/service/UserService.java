package com.bobo.myspringsecurity.admin.service;

import com.bobo.myspringsecurity.admin.dto.MyUserDto;
import com.bobo.myspringsecurity.admin.entity.MyUser;
import com.github.pagehelper.PageInfo;

/**
 * @description: 用户Service
 * @author: bobo
 * @create: 2021-10-07 12:57
 **/
public interface UserService {
    PageInfo<MyUser> getAllUsersByPage(Integer startPosition, Integer limit, MyUserDto user);
    MyUser getUserById(String userId);
    boolean updateUser(MyUser user);
    boolean addUser(MyUser user);
    boolean deleteUser(Integer userId);
}
