package com.jlk.plant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by test on 2016/7/13.
 */
public class Plant {
    @Expose
    @SerializedName("plant_id")
    private String plantId;
    @Expose
    @SerializedName("plant_name")
    private String plantName;
    @Expose
    @SerializedName("type")
    private String plantType;
    @Expose
    @SerializedName("info")
    private String plantInfo;
    @Expose
    @SerializedName("feature")
    private String plantFeature;
    @Expose
    @SerializedName("habit")
    private String plantHabit;
    @Expose
    @SerializedName("use")
    private String plantUse;
    @Expose
    @SerializedName("create_time")
    private String plantCreatTime;
    @Expose
    @SerializedName("cate_id")
    private String categoryId;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("user_id")
    private String userId;

    public Plant(String plantId, String plantName, String plantType,
                 String plantInfo, String plantFeature, String plantHabit,
                 String plantUse, String plantCreatTime, String categoryId,
                 String img, String userId) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantType = plantType;
        this.plantInfo = plantInfo;
        this.plantFeature = plantFeature;
        this.plantHabit = plantHabit;
        this.plantUse = plantUse;
        this.plantCreatTime = plantCreatTime;
        this.categoryId = categoryId;
        this.img = img;
        this.userId = userId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(String plantInfo) {
        this.plantInfo = plantInfo;
    }

    public String getPlantFeature() {
        return plantFeature;
    }

    public void setPlantFeature(String plantFeature) {
        this.plantFeature = plantFeature;
    }

    public String getPlantHabit() {
        return plantHabit;
    }

    public void setPlantHabit(String plantHabit) {
        this.plantHabit = plantHabit;
    }

    public String getPlantUse() {
        return plantUse;
    }

    public void setPlantUse(String plantUse) {
        this.plantUse = plantUse;
    }

    public String getPlantCreatTime() {
        return plantCreatTime;
    }

    public void setPlantCreatTime(String plantCreatTime) {
        this.plantCreatTime = plantCreatTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
}
