package com.jlk.plant.models.requestmodels;

public class GetCategoryListRequest extends BaseRequest {
    protected int page;
    protected int size;

    public GetCategoryListRequest(int page, int size) {
        this.page = page;
        this.size = size;
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
