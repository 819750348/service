package com.snake.web.boot.module.interfaces.exception;

public class NotFoundException extends RuntimeException{


    public Long code;
    public String customMsg;

    public NotFoundException(){};
    public NotFoundException(Long code,String customMsg){
        this.code=code;
        this.customMsg=customMsg;
    }


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(String customMsg) {
        this.customMsg = customMsg;
    }
}
