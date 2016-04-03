package com.jlk.plant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by test on 2016/2/5.
 */
public class Article {
    @Expose
    @SerializedName("article_id")
    private String articleId;
    @Expose
    @SerializedName("title")
    private String arcticleTitle;
    @Expose
    @SerializedName("create_time")
    private String articleCreateTime;
    @Expose
    @SerializedName("content")
    private String articleContent;
    @Expose
    @SerializedName("type_id")
    private String typeId;
    @Expose
    @SerializedName("summary")
    private String articleSummary;
    @Expose
    @SerializedName("img")
    private String img;
    @Expose
    @SerializedName("type_name")
    private String typeName;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArcticleTitle() {
        return arcticleTitle;
    }

    public void setArcticleTitle(String arcticleTitle) {
        this.arcticleTitle = arcticleTitle;
    }

    public String getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(String articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
