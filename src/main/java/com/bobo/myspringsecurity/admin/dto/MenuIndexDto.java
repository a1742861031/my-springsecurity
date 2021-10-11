package com.bobo.myspringsecurity.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 登录后的菜单显示
 * @Date 2021/10/11 16:07
 * @Created by bobo
 */
@Data
public class MenuIndexDto implements Serializable {
    private Integer id;
    private Integer parentId;
    private String title;
    private String icon;
    private Integer type;
    private String href;
    private String permission;
    private List<MenuIndexDto> children;
}

