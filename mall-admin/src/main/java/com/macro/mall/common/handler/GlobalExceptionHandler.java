/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.macro.mall.common.handler;

/**
 * @author lengleng
 * @date 2018/5/24
 */

import com.macro.mall.common.domain.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局的的异常拦截器
 *
 * @author lengleng
 * @date 2018/05/22
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
@Order(1)
public class GlobalExceptionHandler {


    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult accessDeniedException(AccessDeniedException e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        return new CommonResult().failed(477,"当前操作没有权限");
    }



    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public CommonResult exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        return new CommonResult().failed(5001,e.getMessage());
    }
}
