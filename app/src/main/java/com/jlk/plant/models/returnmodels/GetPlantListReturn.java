package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jlk.plant.models.Plant;

import java.util.List;


public class GetPlantListReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<Plant> list; //

    public List<Plant> getList() {
        return list;
    }

    public void setList(List<Plant> list) {
        this.list = list;
    }


}
