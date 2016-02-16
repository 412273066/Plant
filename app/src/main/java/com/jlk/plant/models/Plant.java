package com.jlk.plant.models;

/**
 * Created by test on 2016/2/16.
 */
public class Plant {
    private String plantId;
    private String plantName;
    private String plantType;
    private String plantInfo;
    private String plantFeature;
    private String plantHabit;
    private String plantUse;
    private String plantCreatTime;

    private String img;

    public Plant(String plantId, String plantName, String plantType, String plantInfo, String plantFeature, String plantHabit, String plantUse, String plantCreatTime, String img) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantType = plantType;
        this.plantInfo = plantInfo;
        this.plantFeature = plantFeature;
        this.plantHabit = plantHabit;
        this.plantUse = plantUse;
        this.plantCreatTime = plantCreatTime;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
