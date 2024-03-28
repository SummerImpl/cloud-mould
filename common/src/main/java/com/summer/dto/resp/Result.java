package com.summer.dto.resp;

import com.summer.dto.enums.ExceptinCodeEnum;

/**
 * @Description 统一的返回类
 * @Author pipe
 * @Date 2024/3/28 17:38
 */
public class Result<T> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 请求返回数据
     */
    private T data;

    public Result() {
    }

    public Result(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ExceptinCodeEnum.SUCCESS.getCode());
        result.setMessage(ExceptinCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
