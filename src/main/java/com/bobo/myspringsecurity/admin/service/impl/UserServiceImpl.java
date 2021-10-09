package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.RoleUserDao;
import com.bobo.myspringsecurity.admin.dao.UserDao;
import com.bobo.myspringsecurity.admin.dto.MyUserDto;
import com.bobo.myspringsecurity.admin.entity.MyUser;
import com.bobo.myspringsecurity.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 用户service实现
 * @author: bobo
 * @create: 2021-10-07 12:59
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public PageInfo<MyUser> getAllUsersByPage(Integer pageNum, Integer pageSize, MyUserDto user) {
        PageHelper.startPage(pageNum, pageSize);
        List<MyUser> userList = userDao.getUserList(user);
        PageInfo<MyUser> myUserPageInfo = new PageInfo<>(userList);
        return myUserPageInfo;
    }

    @Override
    public MyUser getUserById(String userId) {
        Integer roleId = roleUserDao.getRoleIdByUserId(userId);
        MyUser user = userDao.getUserById(userId);
        user.setRoleId(roleId);
        return user;
    }

    @Override
    @Transactional
    public boolean updateUser(MyUser user) {
        roleUserDao.updateRoleUser(user.getRoleId(), user.getUserId());
        userDao.updateUser(user);
        return true;
    }

    @Override
    @Transactional
    public boolean addUser(MyUser user) {
        user.setPassword("123456");
        user.setDeptId(1);
        int insert = userDao.insert(user);
        if (insert != 0) {
            roleUserDao.insertRoleUser(user.getRoleId(), user.getUserId());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteUser(Integer userId) {
        userDao.deleteUser(userId);
        roleUserDao.deleteUser(userId);
        return true;
    }
}
