package com.bobo.myspringsecurity.admin.service;

import com.bobo.myspringsecurity.admin.dto.RoleDto;
import com.bobo.myspringsecurity.admin.entity.MyRole;
import io.swagger.models.auth.In;

import java.util.List;

public interface RoleService {
    MyRole getRoleByRoleId(Integer roleId);
    List<MyRole> getRoleList(String roleName);
    boolean addRole(RoleDto role);
    RoleDto getRoleById(Integer roleId);
    boolean editRole(RoleDto role);
    boolean deleteRole(Integer roleId);
}
