package com.rick.springboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rick
 * @createdAt 2021-10-19 21:11:00
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping
    public String sayHello() {
        return "hello world";
    }
}
