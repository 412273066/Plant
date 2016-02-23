package com.jlk.plant.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by test on 2016/2/15.
 */
public class OkhttpUtils {

    private String json;
    private String url;
    private OnHttpPostListener httpPostListener;

    public static final int POST_SUCCESS = 200;
    public static final int POST_FAIL = 400;


    public OkhttpUtils(String json, String url) {
        this.json = json;
        this.url = url;

    }

    public OkhttpUtils(String json, String url, OnHttpPostListener httpPostListener) {
        this.json = json;
        this.url = url;
        this.httpPostListener = httpPostListener;
    }


    public void doPost() {
        if(this.httpPostListener!=null){
            httpPostListener.onPrePostListener();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();

        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder().url(url).post(body).build();

        Call call = client.newCall(request);

        MyCallback callback = new MyCallback();

        call.enqueue(callback);

    }


    public void doGet() {

        if(this.httpPostListener!=null){
            httpPostListener.onPrePostListener();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        //请求加入调度
        MyCallback callback = new MyCallback();

        call.enqueue(callback);

    }

    /**
     * 设置调用成功监听器
     *
     * @author JHong
     */
    public void setOnHttpPostListener(OnHttpPostListener listener) {
        if (listener != null) {
            this.httpPostListener = listener;
            return;
        }
        throw new NullPointerException("OnPostFinishListener is null");
    }


    /**
     * 调用成功回调接口
     *
     * @author JHong
     */
    public static abstract interface OnHttpPostListener {
//      public abstract void onPostFinishListener(int statusCode, Call call, Objects objects);

        public abstract void onPostSuccessListener(Call call, Response response);

        public abstract void onPostFailListener(Call call, IOException e);

        public abstract void onPrePostListener();
    }

    private class MyCallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            if (httpPostListener != null) {
                httpPostListener.onPostFailListener(call, e);
            }
        }

        @Override
        public void onResponse(Call call, Response response) {
            if (httpPostListener != null) {
                httpPostListener.onPostSuccessListener(call, response);
            }

        }

    }
}