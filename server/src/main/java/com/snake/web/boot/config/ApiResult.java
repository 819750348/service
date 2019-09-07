package com.snake.web.boot.config;

import java.io.Serializable;

/**
 * Created by HP on 2018/10/25.
 */
public class ApiResult implements Serializable {
    private static final String message_success = "success";
    private static final String message_error = "error";
    private static final int code_success = 0;

    public static final ApiResult SUCCESSFUL = new ApiResult(code_success, message_success);
    private int code = 0;
    private String message;
    private Object data;

    public static final ApiResult success(Object data) {
        return new ApiResult(data);
    }

    public static final ApiResult error(ApiResultType type, String message) {
        return new ApiResult(type.getCode(), message);
    }

    public static final ApiResult error(int code, String message) {
        return new ApiResult(code, message);
    }

    private ApiResult() {

    }

    public ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(int code, String message) {
        this(code, message, null);
    }

    public ApiResult(int code) {
        this(code, message_error);
    }

    public ApiResult(Object data) {
        this(0, message_success, data);
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
