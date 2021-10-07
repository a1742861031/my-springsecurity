package com.bobo.myspringsecurity.admin.dto;

import lombok.Data;

/**
 * @description: 查询user
 * @author: bobo
 * @create: 2021-10-07 16:09
 **/
@Data
public class MyUserDto {
    private String nickName;

    private String userName;

    private Integer deptId;
}
