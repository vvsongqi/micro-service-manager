package com.pactera.customercenter.domain;

import java.io.UnsupportedEncodingException;

public class DataIsSucces {
       private  String  isSuccess;  //0001表示成功，0002表示失败
       private String desc;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) throws UnsupportedEncodingException {
        if(desc !=null){
            desc = new String(desc.getBytes("GBK"));
        }
        this.desc = desc;
    }
}
