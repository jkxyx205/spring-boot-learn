package com.rick.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rick
 * @createdAt 2021-10-26 15:44:00
 */
@SpringBootApplication
@Controller
public class InterceptorTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterceptorTestApplication.class, args);
    }

    @GetMapping
    public String index() {
        return "index";
    }

}
