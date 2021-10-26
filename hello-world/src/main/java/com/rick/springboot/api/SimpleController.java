package com.rick.springboot.api;

import com.rick.springboot.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rick
 * @createdAt 2021-10-25 15:55:00
 */
@RestController
public class SimpleController {

    @GetMapping("request")
    @ModelAttribute("attr")
    public Map<String, Object> method(String age, User user) {
        Map<String, Object> map  = new HashMap<>();
        map.put("age", age);
        return map;
    }
}
