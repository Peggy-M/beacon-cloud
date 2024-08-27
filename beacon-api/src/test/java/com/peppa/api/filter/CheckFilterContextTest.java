package com.peppa.api.filter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: peppa
 * @Description: 启动类测试
 * @Date: Created in 23:28 2024/8/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CheckFilterContextTest {

    @Autowired
    private CheckFilterContext checkFilterContext;

    @Test
    public void testCheck() {
        Object obj=new Object();
        checkFilterContext.check(obj);
    }
}