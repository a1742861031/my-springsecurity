package com.bobo.myspringsecurity.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description 返回菜单视图
 * @Date 2021/10/8 13:22
 * @Created by bobo
 */
@Data
public class MenuVo {

    private Integer menuId;

    private Integer parentId;

    private String menuName;

    List<MenuVo> children;
}
