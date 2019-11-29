package com.uiho.zlwu.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangyong
 * @Date 2019/11/28 12:02
 */
@Component
public class CorsFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(CorsFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        logger.info("请求路径过滤信息路径为：" + req.getServletPath());

        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE,OPTIONS");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Origin,x-requested-with,Content-Type,Accept,authorization");
        res.setHeader("X-Powered-By","Jetty");
        res.setHeader("Access-Control-Allow-Credentials","true");
        if ("OPTIONS".equals(req.getMethod())) {
            return;
        }
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        logger.info("销毁过滤器方法");
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化过滤器的方法");
    }
}
