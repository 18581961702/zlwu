package com.uiho.zlwu.filter;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义过滤器
 * 浏览器请求接口时，会先发送一个"OPTIONS"的预请求,会出现302的错误,要把这个"OPTIONS"方法的预请求过滤下
 * @Author wangyong
 * @Date 2019/11/28 9:45
 */
public class CORSAuthenticationFilter extends FormAuthenticationFilter{

    Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {
        super();
    }

    /**
     * 解决请求跨域问题
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        HttpServletRequest req=(HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        logger.info("请求路径过滤信息路径为：" + req.getServletPath());
//        logger.info("请求路方法：" + req.getMethod());
//
//        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,OPTIONS");
//        res.setHeader("Access-Control-Max-Age", "3600");
//        res.setHeader("Access-Control-Allow-Headers", "Origin,x-requested-with,Content-Type,Accept,authorization");
//        res.setHeader("X-Powered-By","Jetty");
//        res.setHeader("Access-Control-Allow-Credentials","true");
//        if ("OPTIONS".equals(req.getMethod())) {
//            return true;
//        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 未登录被拦截
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("onAccessDenied:未登录被拦截");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        Map map = new HashMap();
//        map.put("code", Result.NOTLOGIN.getCode());
//        map.put("msg", Result.NOTLOGIN.getMsg());
        map.put("code", "400");
        map.put("msg", "sorry,请先登录");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
        return false;
    }

    /**
     * 登录成功
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        logger.info("onLoginSuccess:登录成功");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        Map map = new HashMap();
        map.put("code", "200");
        map.put("msg", "登录成功_ok");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
        return true;
    }

    /**
     * 登录失败
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        logger.info("onLoginFailure:登录失败");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Map map = new HashMap();
        map.put("code", "400");
        map.put("msg", "登录失败");
        writer.write(JSON.toJSONString(map));
        writer.flush();
        writer.close();
        return false;
    }
}
