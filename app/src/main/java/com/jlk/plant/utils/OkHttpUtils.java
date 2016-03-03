package com.jlk.plant.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by test on 2016/2/15.
 */
public class OkHttpUtils {

    private String json;
    private String url;
    private OnHttpPostListener httpPostListener;

    //    public static final int POST_SUCCESS = 200;
//    public static final int POST_FAIL = 400;
    private int timeout = 10;

    public OkHttpUtils(String json, String url) {
        this.json = json;
        this.url = url;

    }

    public OkHttpUtils(String json, String url, OnHttpPostListener httpPostListener) {
        this.json = json;
        this.url = url;
        this.httpPostListener = httpPostListener;
    }


    public void doPost() {
        if (this.httpPostListener != null) {
            httpPostListener.onPrePostListener();
        }


//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, json);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS).build();

        FormBody.Builder builder = new FormBody.Builder();

        if (json != null) {
            builder.add("json", json);
            L.i(url + ":" + json);
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        Call call = client.newCall(request);

        MyCallback callback = new MyCallback();

        call.enqueue(callback);

    }


    public void doGet() {

        if (this.httpPostListener != null) {
            httpPostListener.onPrePostListener();
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS).build();

        Request request = new Request.Builder().url(url).build();

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
        throw new NullPointerException("OnHttpPostListener is null");
    }


    /**
     * 调用成功回调接口
     *
     * @author JHong
     */
    public interface OnHttpPostListener {
//      public abstract void onPostFinishListener(int statusCode, Call call, Objects objects);

        /**
         * 连接成功
         *
         * @param call
         * @param response
         */
        public abstract void onPostSuccessListener(Call call, Response response);

        /**
         * 连接失败
         *
         * @param call
         * @param e
         */
        public abstract void onPostFailListener(Call call, IOException e);

        /**
         * 连接之前
         */
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
