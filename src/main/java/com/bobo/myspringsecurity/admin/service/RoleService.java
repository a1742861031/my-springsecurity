package com.bobo.myspringsecurity.admin.service;

import com.bobo.myspringsecurity.admin.entity.MyRole;

import java.util.List;

public interface RoleService {
    MyRole getRoleByRoleId(Integer roleId);
    List<MyRole> getRoleList();
}
