package com.macro.mall.portal.domain;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回对象
 * Created by macro on 2018/4/26.
 */
@ApiModel("通用返回对象:CommonResult")
public class CommonResult {
    //操作成功
    public static final int SUCCESS = 200;
    //用户名不存在或密码错误
    public static final int  ERR_USER_INFO = 400;
    //未认证，用户认证失败，请重新登录
    public static final int UNAUTHORIZED = 401;
    //未授权
    public static final int  FORBIDDEN = 403;
    //参数校验失败
    public static final int VALIDATE_FAILED = 404;
    //验证码错误, 请重新输入
    public static final int  ERR_AUTH_CODE = 478;
    //演示环境没有权限操作
    public static final int  DEMO_ENV = 479;

    //操作失败
    public static final int FAILED = 500;
    @ApiModelProperty("编码")
    private int code;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("数据")
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    /**
     * 普通成功返回
     */
    public CommonResult success(String message,Object data) {
        this.code = SUCCESS;
        this.message = message;
        this.data = data;
        return this;
    }

    /**
     * 返回分页成功数据
     */
    @Deprecated
    public CommonResult pageSuccess(Page pageInfo) {
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getSize());
        result.put("totalPage", pageInfo.getTotalPages());
        result.put("total", pageInfo.getTotalElements());
        result.put("pageNum", pageInfo.getNumber());
        result.put("list", pageInfo.getContent());
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = result;
        return this;
    }

    /**
     * 返回分页成功数据
     */
    public CommonResult pageSuccess(List data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = result;
        return this;
    }

    /**
     * 普通失败提示信息
     */
    public CommonResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }
    public CommonResult failed(Integer code,String msg) {
        this.code = code;
        this.message = msg;
        return this;
    }

    public CommonResult failed(String message){
        this.code = FAILED;
        this.message = message;
        return this;
    }
    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult validateFailed(String message) {
        this.code = VALIDATE_FAILED;
        this.message = message;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public CommonResult unauthorized(String message) {
        this.code = UNAUTHORIZED;
        this.message = "暂未登录或token已经过期";
        this.data = message;
        return this;
    }
    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public CommonResult forbidden(String message) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        this.data = message;
        return this;
    }
    /**
     * 参数验证失败使用
     * @param result 错误信息
     */
    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
