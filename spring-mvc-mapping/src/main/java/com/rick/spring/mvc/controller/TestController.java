package com.rick.spring.mvc.controller;

import com.rick.spring.mvc.config.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rick
 * @createdAt 2021-10-26 17:57:00
 */
@RestController
public class TestController {

    @GetMapping("{version}/test")
    public String v1(@PathVariable String version) {
        return "default";
    }

    @GetMapping("{version}/test")
    @ApiVersion("v2")
    public String v2() {
        return "v2";
    }

    @GetMapping("{version}/test")
    @ApiVersion("v3")
    public String v3() {
        return "v3";
    }

    /**
     * version在不一定非要在第一个位置
     * @return
     */
    @GetMapping("/test/{version}")
    @ApiVersion("v4")
    public String v4() {
        return"v4";
    }

    @GetMapping("/test/{version}")
    public String v5() {
        return "default";
    }
}
