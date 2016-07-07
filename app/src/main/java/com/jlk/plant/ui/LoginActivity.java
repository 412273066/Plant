package com.jlk.plant.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;


public class LoginActivity extends BaseFragmentActivity {

    private final String tag = "LoginActivity";
    private EditText edit_username, edit_password;
    private Button btn_login;
    private TextView text_forget_password, text_register;

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
    }


    @Override
    public void initData() {


    }

    @Override
    public String getTitleName() {
        return "登录";
    }


}
