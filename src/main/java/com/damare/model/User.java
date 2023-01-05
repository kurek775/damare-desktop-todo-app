package com.damare.model;

import java.util.List;

public class User {
    private Integer id;
    private String name;

    private String email;
    private String password;
    private List<Integer> friendlist;


    public User(Integer id, String name, String email, String password, List<Integer> friendlist) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.friendlist = friendlist;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getFriendlist() {
        return friendlist;
    }

    public void setFriendlist(List<Integer> friendlist) {
        this.friendlist = friendlist;
    }
}