package com.jlk.plant.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jlk.plant.R;
import com.jlk.plant.app.AppConfig;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.ui.fragment.FragmentOne;
import com.jlk.plant.ui.fragment.FragmentThree;
import com.jlk.plant.ui.fragment.FragmentTwo;


public class MainActivity extends BaseFragmentActivity {
    private RadioGroup mBottomTab;
    private FrameLayout mTabContentFrameLayout;
    private FragmentManager mFragmentManager;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private final String tag = "MainActivity";

    private TextView text_title;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        AppConfig.initImageLoader(mContext);
        AppConfig.initDatabase(mContext);

        if (mTabContentFrameLayout == null)
            mTabContentFrameLayout = (FrameLayout) findViewById(R.id.tab_content);
        if (mBottomTab == null)
            mBottomTab = (RadioGroup) findViewById(R.id.layout_tab);

        text_title = (TextView) findViewById(R.id.title);

    }

    @Override
    public void initListeners() {
        mBottomTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Log.i(TAG, "tab bar is clicked");

                Fragment newFragment = fragmentOne;
                switch (checkedId) {
                    case R.id.one:
                        newFragment = fragmentOne;
                        text_title.setText(getString(R.string.app_name));
                        break;
                    case R.id.two:
                        newFragment = fragmentTwo;
                        text_title.setText("发现");
                        break;
                    case R.id.three:
                        newFragment = fragmentThree;
                        text_title.setText("我的");
                        break;
                    default:
                        break;
                }
                FragmentTransaction transaction = mFragmentManager
                        .beginTransaction();
                transaction.replace(R.id.tab_content, newFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public void initData() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        transaction.replace(R.id.tab_content, fragmentOne);
        transaction.commit();
    }

    @Override
    public String getTitleName() {
        String title = getString(R.string.app_name);
        return title;
    }

    private long exitTime = 1;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出" + getString(R.string.app_name));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
