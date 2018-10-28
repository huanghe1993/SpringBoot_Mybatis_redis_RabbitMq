package com.huanghe.springboot.controller;

import com.huanghe.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: River
 * @Date:Created in  9:28 2018/10/28
 * @Description:
 */
@Controller
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String asyncHello() {
        asyncService.hello();
        return "success";
    }
}
