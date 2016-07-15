package com.jlk.plant.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.app.AppSetting;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.base.CustomApplication;
import com.jlk.plant.models.requestmodels.LoginRequest;
import com.jlk.plant.models.returnmodels.BaseReturn;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.jlk.plant.utils.StringUtils;

import java.io.IOException;


public class LoginActivity extends BaseFragmentActivity {

    private final String tag = "LoginActivity";
    private EditText edit_username, edit_password;
    private Button btn_login;
    private TextView text_forget_password, text_register;
    private String user, password;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        text_forget_password = (TextView) findViewById(R.id.text_forget_password);
        text_register = (TextView) findViewById(R.id.text_register);
    }

    @Override
    public void initListeners() {
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityAnim(null, RegisterActivity.class);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = checkInput();
                if (!StringUtils.isEmpty(msg)) {
                    showToast(msg);
                } else {
                    doLogin();
                }
            }
        });
        text_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DialogUtil.getInstance().createLoadingDialog(mContext,"加载中,请稍后..").show();
            }
        });
    }

    private void doLogin() {
        LoginRequest request = new LoginRequest(user, password);
        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.LOGIN, true);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(String json) {
                try {
                    L.i("返回" + AppInterface.LOGIN + ":" + json);
                    Gson gson = new Gson();
                    final BaseReturn result = gson.fromJson(json, BaseReturn.class);

                    showToast(result.getMsg());
                    if (result.getResCode().equals(AppSetting.code_success)) {
                        CustomApplication app = (CustomApplication) getApplication();
                        app.setLogin(true);
                        finishActivityAnim();
                    } else {

                    }


                } catch (Exception e) {
                    L.e(e.getMessage());
                    showToast("接口出错，开发人员正在修复中。");
                }
            }

            @Override
            public void onPostFailListener(IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onPrePostListener() {

            }
        });


        client.doPost();
    }

    private String checkInput() {
        String msg = null;
        user = edit_username.getText().toString().trim();
        password = edit_password.getText().toString().trim();
        if (StringUtils.isEmpty(user)) {
            msg = "请输入账号。";
        } else if (StringUtils.isEmpty(password)) {
            msg = "请输入密码。";
        }

        return msg;
    }


    @Override
    public void initData() {


    }

    @Override
    public String getTitleName() {
        return "登录";
    }


}
