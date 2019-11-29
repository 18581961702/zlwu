package com.uiho.zlwu.user;

import com.uiho.zlwu.ZlwuApplicationTests;
import com.uiho.zlwu.model.User;
import com.uiho.zlwu.utils.SHA256Util;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wangyong
 * @Date 2019/11/28 11:03
 */
public class UserTest extends ZlwuApplicationTests {

    Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void dePassword(){
        String password = "123456";
        String salt = null;
        String secret = SHA256Util.sha256(password,salt);
        logger.info("加密后数据："+secret);
    }
}
