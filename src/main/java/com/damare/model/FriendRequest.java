package com.damare.model;

public class FriendRequest {

    private Integer id;

    private Integer requesterId;

    private Integer requestedId;

    private Integer statusCode;

    private String statusName;

    public FriendRequest(Integer id, Integer requesterId, Integer requestedId, Integer statusCode, String statusName) {
        this.id = id;
        this.requesterId = requesterId;
        this.requestedId = requestedId;
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Integer getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(Integer requestedId) {
        this.requestedId = requestedId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
