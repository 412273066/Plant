package com.jlk.plant.ui;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.utils.CommonValidator;
import com.jlk.plant.utils.DialogUtil;
import com.jlk.plant.utils.StringUtils;


public class RegisterActivity extends BaseFragmentActivity {

    private final String tag = "LoginActivity";
    private EditText edit_user, edit_password, edit_nickname, edit_captcha, edit_comfirm_password;
    private Button btn_register, btn_get_captcha;

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
