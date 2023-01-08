package com.damare.model;

public class Category
{
    private Integer id;
    private String name;
    private String desc;

    private Integer userid;

    public Category(Integer id, String name, String desc, Integer userid) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userid) {
        this.userid = userid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
