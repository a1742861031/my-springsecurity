package com.bobo.myspringsecurity.common.handler.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bobo
 * @createTime 2021/10/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoboException extends  RuntimeException {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String  msg;
}
