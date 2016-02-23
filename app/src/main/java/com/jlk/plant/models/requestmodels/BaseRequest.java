package com.jlk.plant.models.requestmodels;

public class BaseRequest {
	protected String appKey = "10000";
	protected String appSecret = "";

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
