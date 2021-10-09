package com.bobo.myspringsecurity.admin.service.impl;

import com.bobo.myspringsecurity.admin.dao.RoleDao;
import com.bobo.myspringsecurity.admin.dao.RoleMenuDao;
import com.bobo.myspringsecurity.admin.dto.RoleDto;
import com.bobo.myspringsecurity.admin.entity.MyRole;
import com.bobo.myspringsecurity.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public MyRole getRoleByRoleId(Integer roleId) {
        return roleDao.getRoleByRoleId(roleId);
    }

    @Override
    public List<MyRole> getRoleList(String roleName) {
        return roleDao.getRoleList(roleName);
    }

    @Override
    @Transactional
    public boolean addRole(RoleDto role) {
        roleDao.addRole(role);
        List<Integer> menuIds = role.getMenuIds();

        for (Integer menuId : menuIds) {
            roleMenuDao.addRoleMenu(role.getRoleId(), menuId);
        }
        return true;
    }

    @Override
    public RoleDto getRoleById(Integer roleId) {
        MyRole myRole = roleDao.getRoleById(roleId);
        List<Integer> menuIds = roleMenuDao.getMenuByRoleId(roleId);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(myRole.getRoleId());
        roleDto.setRoleName(myRole.getRoleName());
        roleDto.setDescription(myRole.getDescription());
        roleDto.setMenuIds(menuIds);
        return roleDto;
    }

    @Override
    @Transactional
    public boolean editRole(RoleDto role) {
        //先删除以前的权限数据
        roleMenuDao.deleteByRoleId(role.getRoleId());
        //向MyRole对象中填充数据
        MyRole myRole = new MyRole();
        myRole.setRoleId(role.getRoleId());
        myRole.setRoleName(role.getRoleName());
        myRole.setDescription(role.getDescription());
        //执行修改角色方法
        roleDao.updateRole(myRole);
        List<Integer> menuIds = role.getMenuIds();
        //修改角色对于权限
        for (Integer menuId : menuIds) {
            roleMenuDao.addRoleMenu(role.getRoleId(), menuId);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteRole(Integer roleId) {
        roleMenuDao.deleteByRoleId(roleId);
        roleDao.deleteRole(roleId);
        return true;
    }
}
