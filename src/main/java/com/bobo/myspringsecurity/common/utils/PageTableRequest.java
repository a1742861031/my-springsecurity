package com.bobo.myspringsecurity.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 分页工具类
 * @author: bobo
 * @create: 2021-10-07 12:48
 **/
@Data
public class PageTableRequest implements Serializable {

    private Integer pageNum;//初始页
    private Integer pageSize;//一页几条数据
}

