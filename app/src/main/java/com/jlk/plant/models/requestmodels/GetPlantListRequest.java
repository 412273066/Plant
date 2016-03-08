package com.jlk.plant.models.requestmodels;

public class GetPlantListRequest extends BaseRequest {
    protected int categoryId;
    protected int page;
    protected int size;


    public GetPlantListRequest(int categoryId, int page, int size) {
        this.categoryId = categoryId;
        this.page = page;
        this.size = size;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
