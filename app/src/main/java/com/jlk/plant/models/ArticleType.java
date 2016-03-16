package com.jlk.plant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by test on 2016/2/5.
 */
public class ArticleType {
    @Expose
    @SerializedName("type_id")
    private String typeId;
    @Expose
    @SerializedName("type_name")
    private String typeName;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("create_time")
    private String createTime;
    @Expose
    @SerializedName("user_id")
    private String userId;

    public ArticleType(String typeId, String typeName, String img, String createTime, String userId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.img = img;
        this.createTime = createTime;
        this.userId = userId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
