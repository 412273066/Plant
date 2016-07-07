package com.jlk.plant.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlk.plant.R;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.app.AppSetting;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.utils.OkHttpUtils;
import com.jlk.plant.utils.RegexUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


public class IdentifyActivity extends BaseFragmentActivity {

    private final String tag = "IdentifyActivity";
    private TextView text_result, label_tip;
    private ImageView image;
    private Button btn_upload;
    private File file;
    public static final int NONE = 0;
    public static final int PHOTOZOOM = 2; // 缩放
    public static final int PHOTORESOULT = 3;// 结果
    public static final String IMAGE_UNSPECIFIED = "image/*";

    private String saveName = "img_identified.png";

    private ProgressDialog loadingDialog;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_identity);
    }

    @Override
    public void initViews() {
        findViewById(R.id.back).setVisibility(View.VISIBLE);

        text_result = (TextView) findViewById(R.id.text_result);
        label_tip = (TextView) findViewById(R.id.label_tip);
        image = (ImageView) findViewById(R.id.image);
        btn_upload = (Button) findViewById(R.id.btn_upload);


    }

    @Override
    public void initListeners() {

        label_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PHOTOZOOM);
            }
        });
        btn_upload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (file == null) {
                            Toast.makeText(mContext, "请选择图片!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!file.exists()) {
                            Toast.makeText(mContext, "文件不存在!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        OkHttpUtils client = new OkHttpUtils(mContext, null, AppInterface.BAIDUSHITU);

                        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
                            @Override
                            public void onPostSuccessListener(Call call, Response response) {
                                try {
                                    String result = response.body().string();

                                    String data = RegexUtil.getString(result, "data-word-index=\"0\" target=\"_blank\">[\\u4e00-\\u9fa5]{1,}</a>");

                                    if (data != null) {
                                        int start = data.indexOf(">") + 1;
                                        int end = data.indexOf("<");
                                        data = data.substring(start, end);
                                    } else {
                                        data = "未找到结果";
                                    }

                                    final String finalData = data;

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            text_result.setText("最佳猜测:" + finalData);
                                        }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (loadingDialog != null) {
                                        loadingDialog.dismiss();
                                    }
                                }


                            }

                            @Override
                            public void onPostFailListener(Call call, IOException e) {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                }
                            }

                            @Override
                            public void onPrePostListener() {
                                loadingDialog = new ProgressDialog(mContext);
                                loadingDialog.setCanceledOnTouchOutside(false);
                                loadingDialog.setMessage("上传分析中,请稍后");
                                loadingDialog.show();

                            }
                        });
                        client.doUpload("image", saveName, "image/png", file);

                    }
                }

        );
    }


    @Override
    public void initData() {

    }

    @Override
    public String getTitleName() {
        return "以图识花";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // ContentResolver resolver = getContentResolver();

        if (resultCode == NONE || data == null)
            return;

        // 读取相册缩放图片
        if (requestCode == PHOTOZOOM) {
            try {
                // 获得图片的uri
                Uri originalUri = data.getData();

                startPhotoZoom(originalUri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 处理结果
        if (requestCode == PHOTORESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");

                File dir = new File(AppSetting.sdCard_dir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir, saveName);
                try {
                    // 保存截图
                    FileOutputStream output = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, output);// (0--100)压缩文件
                    image.setImageBitmap(photo);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 开启一个系统页面来裁剪传进来的照片
     *
     * @param uri 需要裁剪的照片的URI值
     */

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }
}
