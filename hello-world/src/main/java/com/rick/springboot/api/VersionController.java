package com.rick.springboot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rick
 * @createdAt 2021-10-21 17:47:00
 */
@RestController
@RequestMapping("{version}/users")
public class VersionController {

//    @GetMapping("get")
//    @ApiVersion("v2")
//    public String version(@PathVariable String version) {
//        return version;
//    }
//
//    @GetMapping("get")
//    public String version2(@PathVariable String version) {
//        return version;
//    }
}
