package com.uiho.zlwu.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author UIHO-N2
 */
@RestControllerAdvice
public class ShiroException {

    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String,Object> defaultErrorHandler(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("403","权限不足");
        return map;
    }
}
