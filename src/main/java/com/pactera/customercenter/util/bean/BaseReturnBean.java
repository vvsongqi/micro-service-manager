package com.pactera.customercenter.util.bean;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class BaseReturnBean {

         private MetaBean  meta;//返回数据码值和描述信息
         private Object   data; //单条数据查询返回信息
         private List<Object> list; //多条数据查询返回列表信息

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }


    public  void setMeta(String code,String message) throws UnsupportedEncodingException {
        meta  = new MetaBean();
        meta.setCode(code);
        if(message!=null){
            message = new String(message.getBytes("GBK"));
            meta.setMessage(message);
        }

        this.meta = meta;
    }
}
