package com.rick.springboot.model;


import javax.validation.constraints.NotBlank;

/**
 * @author Rick
 * @createdAt 2021-10-20 13:45:00
 */
public class User {

    private Integer age;

    @NotBlank
    private String name;

    private Cat cat;

    public User() {
    }

    public User(Cat cat) {
        this.cat = cat;
    }

    public User(Integer age, Cat cat) {
        this.age = age;
        this.cat = cat;
    }

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

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", cat=" + cat +
                '}';
    }
}
