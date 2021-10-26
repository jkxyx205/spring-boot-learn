package com.rick.spring.mvc.model;


import javax.validation.constraints.NotBlank;

/**
 * @author Rick
 * @createdAt 2021-10-20 13:45:00
 */
public class User {

    private Integer age;

    @NotBlank
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
