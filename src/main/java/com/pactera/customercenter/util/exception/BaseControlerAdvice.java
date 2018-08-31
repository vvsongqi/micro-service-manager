package com.pactera.customercenter.util.exception;


import com.pactera.customercenter.util.bean.MetaBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
controler  增强器
 */
@RestControllerAdvice
public class BaseControlerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("message", ex.getMessage());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseExcetion.class)
    public Map myErrorHandler(BaseExcetion ex) {
        Map map = new HashMap();
        MetaBean metaBean = new MetaBean();
        metaBean.setCode(ex.getCode());
        metaBean.setMessage(ex.getMessage());
        map.put("meta",metaBean);
//        map.put("code", ex.getCode());
//        map.put("message", ex.getMessage());
        return map;
    }


}
