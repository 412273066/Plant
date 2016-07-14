package com.jlk.plant.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragment;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.base.CustomApplication;
import com.jlk.plant.ui.LoginActivity;
import com.jlk.plant.ui.SettingActivity;


/**
 * 健康管理fragment
 *
 * @author jlk
 */
public class FragmentThree extends BaseFragment {
    private String tag = "FragmentThree";
    private TextView title;// 标题


    private RelativeLayout relative_collection, relative_feedback, relative_share, relative_setting;
    private Button btn_login;
    private TextView text_username, text_level;
    private ImageView img_user;

    private CustomApplication app;

    @Override
    public void initData() {
    }

    @Override
    public void initListeners() {
        relative_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relative_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relative_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relative_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
                activity.startActivityAnim(null, SettingActivity.class);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
                activity.startActivityAnim(null, LoginActivity.class);
            }
        });
    }

    @Override
    public void initViews() {
        relative_collection = (RelativeLayout) mRootView.findViewById(R.id.relative_collection);
        relative_feedback = (RelativeLayout) mRootView.findViewById(R.id.relative_feedback);
        relative_share = (RelativeLayout) mRootView.findViewById(R.id.relative_share);
        relative_setting = (RelativeLayout) mRootView.findViewById(R.id.relative_setting);
        btn_login = (Button) mRootView.findViewById(R.id.btn_login);
        text_username = (TextView) mRootView.findViewById(R.id.text_username);
        text_level = (TextView) mRootView.findViewById(R.id.text_level);
        img_user = (ImageView) mRootView.findViewById(R.id.img_user);

        app = (CustomApplication) getActivity().getApplication();
    }

    @Override
    public int setRootViewResourceId() {
        return R.layout.fragment_three;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (app.isLogin) {
            btn_login.setVisibility(View.GONE);
            text_username.setVisibility(View.VISIBLE);
            text_level.setVisibility(View.VISIBLE);
            img_user.setImageResource(R.mipmap.ic_user_male);
        } else {
            btn_login.setVisibility(View.VISIBLE);
            text_username.setVisibility(View.GONE);
            text_level.setVisibility(View.GONE);
            img_user.setImageResource(R.mipmap.ic_user_default);
        }
    }
}
