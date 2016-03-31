package com.jlk.plant.models.requestmodels;

public class SearchPlantRequest extends BaseRequest {
    protected String keyword;

    public SearchPlantRequest(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
