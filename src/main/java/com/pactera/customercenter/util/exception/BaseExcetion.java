package com.pactera.customercenter.util.exception;

public class BaseExcetion extends  RuntimeException{

       private String  code;
       private String  message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  BaseExcetion(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
