package com.jlk.plant.ui;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jlk.plant.R;
import com.jlk.plant.app.AppInterface;
import com.jlk.plant.app.AppSetting;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.models.requestmodels.RegisterRequest;
import com.jlk.plant.models.returnmodels.BaseReturn;
import com.jlk.plant.utils.CommonValidator;
import com.jlk.plant.utils.DialogUtil;
import com.jlk.plant.utils.L;
import com.jlk.plant.utils.OkHttpUtils;
import com.jlk.plant.utils.StringUtils;

import java.io.IOException;


public class RegisterActivity extends BaseFragmentActivity {

    private final String tag = "LoginActivity";
    private EditText edit_user, edit_password, edit_nickname, edit_captcha, edit_comfirm_password;
    private Button btn_register, btn_get_captcha;
    private String user, password, nickname, captcha, comfirm_pass;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initViews() {
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        edit_user = (EditText) findViewById(R.id.edit_user);
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_nickname = (EditText) findViewById(R.id.edit_nickname);
        edit_captcha = (EditText) findViewById(R.id.edit_captcha);
        edit_comfirm_password = (EditText) findViewById(R.id.edit_comfirm_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_get_captcha = (Button) findViewById(R.id.btn_get_captcha);


    }

    @Override
    public void initListeners() {
        btn_get_captcha.setOnClickListener(getver);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = checkInput();
                if (!StringUtils.isEmpty(msg)) {
                    showToast(msg);
                } else {
                    doRegister();
                }

            }
        });

    }

    private void doRegister() {

        RegisterRequest request = new RegisterRequest(user, password, comfirm_pass, nickname, captcha);
        String json = new Gson().toJson(request);

        OkHttpUtils client = new OkHttpUtils(mContext, json, AppInterface.REGISTER, true);

        client.setOnHttpPostListener(new OkHttpUtils.OnHttpPostListener() {
            @Override
            public void onPostSuccessListener(String json) {
                try {
                    L.i("返回" + AppInterface.REGISTER + ":" + json);
                    Gson gson = new Gson();
                    final BaseReturn result = gson.fromJson(json, BaseReturn.class);


                    showToast(result.getMsg());
                    if (result.getResCode().equals(AppSetting.code_success)) {
                        finishActivityAnim();
                    } else {

                    }


                } catch (Exception e) {
                    L.e(e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("接口出错，开发人员正在修复中。");
                        }
                    });
                }
            }

            @Override
            public void onPostFailListener(IOException e) {
            }

            @Override
            public void onPrePostListener() {

            }
        });


        client.doPost();


    }

    private String checkInput() {

        user = edit_user.getText().toString().trim();
        password = edit_password.getText().toString().trim();
        comfirm_pass = edit_comfirm_password.getText().toString().trim();
        nickname = edit_nickname.getText().toString().trim();
        captcha = edit_captcha.getText().toString().trim();

        String msg = null;

        if (StringUtils.isEmpty(user)) {
            msg = "请输入用户名(手机号)";
        } else if (!CommonValidator.isMobileNO(user)) {
            msg = "用户名必须为11位手机号码";
        } else if (StringUtils.isEmpty(password)) {
            msg = "请输入密码";
        } else if (password.length() < 6) {
            msg = "请输入大于6位密码";
        } else if (StringUtils.isEmpty(comfirm_pass)) {
            msg = "请输入确认密码";
        } else if (!password.equals(comfirm_pass)) {
            msg = "密码不一致";
        } else if (StringUtils.isEmpty(nickname)) {
            msg = "请输入昵称";
        } else if (StringUtils.isEmpty(captcha)) {
            msg = "请输入验证码";
        }


        return msg;
    }


    @Override
    public void initData() {

    }

    @Override
    public String getTitleName() {
        return "注册";
    }

    View.OnClickListener getver = new View.OnClickListener() {
        private final Handler handler = new Handler();
        private boolean canClick = true;

        @Override
        public void onClick(View arg0) {
            final String phone = edit_user.getText().toString();
            if (!StringUtils.isEmpty(phone)) {
                if (!CommonValidator.isMobileNO(phone) || phone.length() != 11) {
                    String msg = "用户名必须为11位手机号码";
                    DialogUtil.getInstance()
                            .createDialogCommitBtnAutoDismiss(mContext,
                                    getString(R.string.label_title_tip), msg)
                            .show();
                } else {
                    if (canClick) {
//                        SendCAPTCHAAction requireCAPTCHA = new SendCAPTCHAAction();
//                        requireCAPTCHA.SendCaptchaRequest(phone, mContext);
                        canClick = false;
                        btn_get_captcha.setClickable(false);
                        final Runnable task = new Runnable() {
                            private int i = 120;

                            @Override
                            public void run() {
                                if (i > 0) {
                                    btn_get_captcha.setText("重新获取(" + i
                                            + ")");
                                    handler.postDelayed(this, 1000);
                                    i--;
                                } else {
                                    btn_get_captcha
                                            .setText(getString(R.string.get_captcha));
                                    btn_get_captcha.setClickable(true);
                                    canClick = true;
                                }
                            }
                        };
                        handler.post(task);
                    }
                }
            } else {
                DialogUtil
                        .getInstance()
                        .createDialogCommitBtnAutoDismiss(mContext,
                                getString(R.string.label_title_tip),
                                "请先输入用户名(手机号)").show();
            }
        }
    };
}
