package com.jlk.plant.models.requestmodels;

public class GetArticleListRequest extends BaseRequest {
    protected int typeId;
    protected int page;
    protected int size;

    public GetArticleListRequest(int typeId, int page, int size) {
        this.typeId = typeId;
        this.page = page;
        this.size = size;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
