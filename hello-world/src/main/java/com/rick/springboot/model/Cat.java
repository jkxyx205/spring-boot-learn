package com.rick.springboot.model;

/**
 * @author Rick
 * @createdAt 2021-10-20 13:45:00
 */
public class Cat {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
