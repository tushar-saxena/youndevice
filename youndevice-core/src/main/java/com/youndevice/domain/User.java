package com.youndevice.domain;

import java.lang.Long;
import java.lang.String;

public class User {
    private Long id;

    private String name;

    Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
