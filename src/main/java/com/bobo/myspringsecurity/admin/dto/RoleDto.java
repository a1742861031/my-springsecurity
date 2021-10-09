package com.bobo.myspringsecurity.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description 传输角色信息
 * @Date 2021/10/8 20:16
 * @Created by bobo
 */
@Data
public class RoleDto {
    private Integer roleId;
    private String roleName;
    private String description;
    private List<Integer> menuIds;
}
