package com.jlk.plant.ui;

import android.view.View;
import android.webkit.WebView;

import com.jlk.plant.R;
import com.jlk.plant.base.BaseFragmentActivity;
import com.jlk.plant.utils.L;


public class ArticleDetailActivity extends BaseFragmentActivity {

    private final String tag = "ArticleDetailActivity";
    private WebView webView;

    @Override
    public void setActivityContext() {
        mContext = this;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_article_detail);
    }

    @Override
    public void initViews() {

        findViewById(R.id.back).setVisibility(View.VISIBLE);
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    public void initListeners() {

    }


    @Override
    public void initData() {
        String url = getIntent().getStringExtra("url");
        L.i("url:" + url);
        webView.loadUrl(url);
    }

    @Override
    public String getTitleName() {
        return null;
    }


}
