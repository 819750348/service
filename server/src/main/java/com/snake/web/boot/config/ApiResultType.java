package com.snake.web.boot.config;

public enum ApiResultType {
    SUCCESS(0,"成功"),
    NO_PERMISSION(211,"权限不足"),
    SERVER_ERROR(10000,"服务器异常"),
    AUTH_ERROR(10001,"认证失败"),
    PARAMS_ERROR(10002,"参数错误"),
    JSON_PARSE_ERROR(10003,"Json解析错误"),
    ILLEGAL_PARAMETER(15001,"非法的参数"),
    ILLEGAL_STRING(15001,"非法字符串"),
    UNKNOWN_ERROR(16000,"未知错误"),
    BUSINESS_ERROR(17000,"业务错误");


    private int code;
    private String msg;

    ApiResultType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

