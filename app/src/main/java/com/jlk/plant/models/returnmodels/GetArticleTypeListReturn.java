package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlk.plant.models.ArticleType;
import com.jlk.plant.models.Category;

import java.util.List;


public class GetArticleTypeListReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<ArticleType> list; //

    public List<ArticleType> getList() {
        return list;
    }

    public void setList(List<ArticleType> list) {
        this.list = list;
    }
}
