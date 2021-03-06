package com.bobo.myspringsecurity.admin.entity;

import lombok.Data;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Data
public class MyMenu extends BaseEntity{

    private static final long serialVersionUID = -6525908145032868815L;

    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String icon;

    private Integer type;

    private String url;

    private String permission;

    private Integer sort;
    private boolean hasChildren = true; //默认情况下 为true
}
