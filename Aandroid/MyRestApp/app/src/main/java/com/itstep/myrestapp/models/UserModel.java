package com.itstep.myrestapp.models;

import java.time.LocalDateTime;
import java.util.Date;

public class UserModel {
    private int id;
    private String name;

    private String avatar;
    private Date createdAt;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatarUrl) {
        this.avatar = avatarUrl;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

