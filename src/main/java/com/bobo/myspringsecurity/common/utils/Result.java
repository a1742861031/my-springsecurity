package com.bobo.myspringsecurity.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 统一返回结果
 * @author: bobo
 * @create: 2021-10-07 11:03
 **/
//统一返回结果的类
@Data
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "总数")
    private Integer count;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private Result() {}

    //成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("成功");
        return r;
    }

    //失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("失败");
        return r;
    }
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result message(String message){
        this.setMsg(message);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result<T> data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public Result count(Integer count){
        this.count = count;
        return this;
    }
}
