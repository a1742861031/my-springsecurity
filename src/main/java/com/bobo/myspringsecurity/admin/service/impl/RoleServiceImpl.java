package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.RoleDao;
import com.bobo.myspringsecurity.admin.dao.RoleUserDao;
import com.bobo.myspringsecurity.admin.entity.MyRole;
import com.bobo.myspringsecurity.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: role表
 * @author: bobo
 * @create: 2021-10-07 17:54
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public MyRole getRoleByRoleId(Integer roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }
    @Override
    public List<MyRole> getRoleList() {
        return roleDao.getRoleList();
    }
}
