package com.jlk.plant.models;

/**
 * Created by test on 2016/2/5.
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private String img;
    private String createTime;
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
