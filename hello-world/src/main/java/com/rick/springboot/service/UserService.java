package com.rick.springboot.service;

import com.rick.springboot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Rick
 * @createdAt 2021-10-22 11:50:00
 */
@Service
@Validated
public class UserService {

//    @Validated
    public void save(@Valid User user,
                     @Min(value = 22, message = "不能小于22岁") Integer age) {
        System.out.println("userService save...");
    }

//    @Validated
    public void save2(@NotBlank String title) {
        System.out.println("userService save3...");
    }
}
