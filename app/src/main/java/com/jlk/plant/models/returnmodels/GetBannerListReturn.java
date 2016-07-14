package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlk.plant.models.Banner;

import java.util.List;


public class GetBannerListReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<Banner> list; //

    public List<Banner> getList() {
        return list;
    }

    public void setList(List<Banner> list) {
        this.list = list;
    }


}
