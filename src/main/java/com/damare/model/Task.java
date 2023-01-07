package com.damare.model;

import java.util.Date;
import java.util.List;

public class Task {
    private Integer id;
    private Integer catId;
    private Integer userId;
    private Integer importance;
    private Integer duration;
    private String name;
    private String place;

    private String description;
    private Date date;
    private Boolean status;

    public Task(Integer id, Integer catId,
                Integer userId, Integer importance,
                Integer duration, String name,
                String place, 
                String description, Date date,
                Boolean status) {
        this.id = id;
        this.catId = catId;
        this.userId = userId;
        this.importance = importance;
        this.duration = duration;
        this.name = name;
        this.place = place;

        this.description = description;
        this.date = date;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
