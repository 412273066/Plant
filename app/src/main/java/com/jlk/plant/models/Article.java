package com.jlk.plant.models;

/**
 * Created by test on 2016/2/5.
 */
public class Article {
    private String articleId;
    private String arcticleTitle;
    private String articleCreateTime;
    private String articleContent;
    private String articleAuthor;
    private String articleType;
    private String articleKeywords;
    private String articleClick;
    private String articleSummary;
    private String img;

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

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleKeywords() {
        return articleKeywords;
    }

    public void setArticleKeywords(String articleKeywords) {
        this.articleKeywords = articleKeywords;
    }

    public String getArticleClick() {
        return articleClick;
    }

    public void setArticleClick(String articleClick) {
        this.articleClick = articleClick;
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

    public Article(String articleId, String arcticleTitle, String articleCreateTime, String articleContent, String articleAuthor, String articleType, String articleKeywords, String articleClick, String articleSummary, String img) {
        this.articleId = articleId;
        this.arcticleTitle = arcticleTitle;
        this.articleCreateTime = articleCreateTime;
        this.articleContent = articleContent;
        this.articleAuthor = articleAuthor;
        this.articleType = articleType;
        this.articleKeywords = articleKeywords;
        this.articleClick = articleClick;
        this.articleSummary = articleSummary;
        this.img = img;
    }
}
