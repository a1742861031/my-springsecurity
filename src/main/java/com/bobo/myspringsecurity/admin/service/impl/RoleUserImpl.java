package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.RoleUserDao;
import com.bobo.myspringsecurity.admin.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: role_user
 * @author: bobo
 * @create: 2021-10-07 17:43
 **/
@Service
public class RoleUserImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Integer getRoleIdByUserId(String UserId) {
        return roleUserDao.getRoleIdByUserId(UserId);
    }

}
