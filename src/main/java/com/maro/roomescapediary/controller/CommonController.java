package com.maro.roomescapediary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/ping")
    private String ping() {
        return "HelloWorld";
    }
}
