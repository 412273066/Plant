package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BaseReturn {
    @Expose
    @SerializedName("detailMsg")
    private String detailMsg; // 返回详细信息
    @Expose
    @SerializedName("msg")
    private String msg; // 返回提示信息值
    @Expose
    @SerializedName("resCode")
    private String resCode; // 返回结果值

//	@Expose
//	@SerializedName("guid")
//	private String guid; // 返回结果值


    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
