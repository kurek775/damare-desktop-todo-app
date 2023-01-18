package com.damare.model;

public class LeaderBoard {
    private Integer id;
    private String name;

    private String email;
    private Integer taskCompletedCount;



    public LeaderBoard(Integer id, String name, String email, Integer taskCompletedCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.taskCompletedCount = taskCompletedCount;

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

    public Integer getTaskCompletedCount() {
        return taskCompletedCount;
    }

    public void setTaskCompletedCount(Integer taskCompletedCount) {
        this.taskCompletedCount = taskCompletedCount;
    }


}
