package com.jlk.plant.models.requestmodels;

/**
 * Created by Administrator on 2016/3/5.
 */
public class GetCategoryListRequest extends BaseRequest{
    private int page;
    private int size;

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

    public GetCategoryListRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
