package com.jlk.plant.models;

/**
 * Created by test on 2016/2/5.
 */
public class User {
    private String userId;
    private String password;
    private String username;
    private String sex;
    private String level;
    private String createTime;

    public User(String userId, String password, String username, String sex, String level, String createTime) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.sex = sex;
        this.level = level;
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
