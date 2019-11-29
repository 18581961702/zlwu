package com.uiho.zlwu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZlwuApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("test");
    }

    @BeforeAll
    public static void init() {
        System.out.println("开始测试-----------------");
    }

    @AfterAll
    public static void after() {
        System.out.println("测试结束-----------------");
    }

}
