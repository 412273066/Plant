package com.jlk.plant.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by test on 2016/2/15.
 */
public class OkHttpUtils {

    private String json;
    private String url;
    private boolean isShowLoadingDialog;
    private OnHttpPostListener httpPostListener;

    private int timeout = 10;

    private Handler mHandler;
    private Context mContext;
    private Dialog loadingDialog;

    public OkHttpUtils(Context mContext, String json, String url) {
        this(mContext, json, url, false);
    }

    public OkHttpUtils(Context mContext, String json, String url, boolean isShowLoadingDialog) {
        this(mContext, json, url, isShowLoadingDialog, null);
    }

    public OkHttpUtils(Context mContext, String json, String url, boolean isShowLoadingDialog, OnHttpPostListener httpPostListener) {
        this.json = json;
        this.url = url;
        this.mContext = mContext;
        this.httpPostListener = httpPostListener;
        this.isShowLoadingDialog = isShowLoadingDialog;
        this.mHandler = new Handler();
    }

    /**
     * post方式提交
     */

    public void doPost() {
        if (this.httpPostListener != null) {
            httpPostListener.onPrePostListener();
        }

        if (isShowLoadingDialog) {
            loadingDialog = DialogUtil.getInstance().createLoadingDialog(mContext);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setCancelable(true);
            loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });
            loadingDialog.show();
        }
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, json);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS).build();

        FormBody.Builder builder = new FormBody.Builder();

        if (!StringUtils.isEmpty(json)) {
            builder.add("json", json);
            L.i("请求" + url + ":" + json);
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        Call call = client.newCall(request);

        MyCallback callback = new MyCallback();

        call.enqueue(callback);

    }

    /**
     * get方式提交
     */
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
     * 上传单个文件
     *
     * @param key       表单中input标签 name的值
     * @param fileName  文件名
     * @param mediaType 媒体类型
     * @param file      上传的文件
     */
    public void doUpload(String key, String fileName, String mediaType, File file) {

        if (this.httpPostListener != null) {
            httpPostListener.onPrePostListener();
        }


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS).build();

        RequestBody fileBody = RequestBody.create(MediaType.parse(mediaType), file);

        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart(key, fileName, fileBody)
                .setType(MediaType.parse("multipart/form-data"))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
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
         * @param response
         */
        public abstract void onPostSuccessListener(String response);

        /**
         * 连接失败
         *
         * @param e
         */
        public abstract void onPostFailListener(IOException e);

        /**
         * 连接之前
         */
        public abstract void onPrePostListener();
    }

    private class MyCallback implements Callback {
        @Override
        public void onFailure(final Call call, final IOException e) {
            L.e(e.getMessage());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                    Toast.makeText(mContext, "加载失败,请检查网络是否正常!", Toast.LENGTH_SHORT).show();
                    if (httpPostListener != null) {
                        httpPostListener.onPostFailListener(e);
                    }
                }
            });


        }

        @Override
        public void onResponse(final Call call, final Response response) {
            String res = null;
            try {
                res = response.body().string();
            } catch (IOException e) {
                L.e(e.getMessage());
            }
            final String finalRes = res;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                    if (httpPostListener != null)
                        httpPostListener.onPostSuccessListener(finalRes);
                }
            });
        }
    }
}
