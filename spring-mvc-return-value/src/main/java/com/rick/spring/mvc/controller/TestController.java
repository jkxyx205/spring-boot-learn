package com.rick.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rick
 * @createdAt 2021-10-26 20:11:00
 */
@Controller
public class TestController {

    @GetMapping("index")
    public String toPage() {
        return "index";
    }

    @GetMapping("home")
    @ResponseBody
    public String writeString() {
        return "home";
    }

    @GetMapping("int/{intValue}")
    public int toIntPage(@PathVariable Integer intValue) {
        return intValue;
    }
}
