package com.uiho.zlwu.utils;

import com.alibaba.fastjson.JSON;
import com.uiho.zlwu.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @Author wangyong
 * @Date 2019/11/26 17:57
 */
public class ShiroUtil {

    public static Logger logger = LoggerFactory.getLogger(ShiroUtil.class);

    private ShiroUtil() {
    }

    /**
     * 获取当前用户Session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout(){
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getUserInfo(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static void deleteCache(String account){
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        //获取当前已登录用户的session
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : sessions) {
            //清除该用户以前保存的session
            if ((String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))).contains(account)) {
                sessionManager.getSessionDAO().delete(session);
                break;
            }
        }
    }
}
