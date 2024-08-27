package com.peppa.api.controller;

import com.peppa.api.filter.CheckFilterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: peppa
 * @Description: TODO
 * @Date: Created in 0:13 2024/8/28
 */
@RestController
public class TestController {


    @Autowired
    private CheckFilterContext checkFilterContext;

    @GetMapping("/api/test")
    public void testCheck() {
        Object obj=new Object();
        checkFilterContext.check(obj);
    }
}
