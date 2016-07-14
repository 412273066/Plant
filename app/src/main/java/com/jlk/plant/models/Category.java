package com.jlk.plant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by test on 2016/7/13.
 */
public class Category {
    @Expose
    @SerializedName("cate_id")
    private String categoryId;
    @Expose
    @SerializedName("cate_name")
    private String categoryName;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("create_time")
    private String createTime;
    @Expose
    @SerializedName("user_id")
    private String userId;

    public Category(String categoryId, String categoryName, String img, String createTime, String userId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.img = img;
        this.createTime = createTime;
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}