package com.jlk.plant.models;

/**
 * Created by test on 2016/2/5.
 */
public class Banner {
    private String bannerId;
    private String title;
    private String content;
    private String img;
    private String userId;
    private String createTime;

    public Banner(String bannerId, String title, String content, String img, String userId, String createTime) {
        this.bannerId = bannerId;
        this.title = title;
        this.content = content;
        this.img = img;
        this.userId = userId;
        this.createTime = createTime;
    }

    public Banner() {

    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
