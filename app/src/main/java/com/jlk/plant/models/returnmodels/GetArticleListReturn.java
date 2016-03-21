package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlk.plant.models.Article;

import java.util.List;


public class GetArticleListReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<Article> list; //

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }
}
