package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlk.plant.models.Category;

import java.util.List;


public class GetCategoryListReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<Category> list; //

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
}
