package com.jlk.plant.models;

/**
 * Created by test on 2016/2/5.
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private String img;

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

    public Category(String categoryId, String categoryName, String img) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.img = img;
    }
}
