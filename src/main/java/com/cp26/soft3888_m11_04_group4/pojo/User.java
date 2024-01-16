package com.cp26.soft3888_m11_04_group4.pojo;

import org.springframework.stereotype.Component;

/**
 * @author xiaonan
 */
@Component
public class User {

    private Integer id;

    private String name;

    private String password;

    private String title;

    public User() {
    }

    public User(int id, String name, String password, String title) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
