package com.uiho.zlwu.shiro;

import com.uiho.zlwu.controller.UserController;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 自定义SessionId生成器
 * @Author wangyong
 * @Date 2019/11/26 19:02
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    Logger logger = LoggerFactory.getLogger(ShiroSessionIdGenerator.class);

    @Override
    public Serializable generateId(Session session) {
        logger.info("sessionId生成器");
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s",sessionId);
    }
}
