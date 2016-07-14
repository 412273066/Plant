package com.jlk.plant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by test on 2016/7/13.
 */
public class Banner {
    @Expose
    @SerializedName("banner_id")
    private String bannerId;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("user_id")
    private String userId;
    @Expose
    @SerializedName("create_time")
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}