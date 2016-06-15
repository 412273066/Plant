package com.jlk.plant.ui;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

        // 启用支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initListeners() {

    }


    @Override
    public void initData() {
        String url = getIntent().getExtras().getString("url");
        L.i("url:" + url);
        webView.loadUrl(url);
        webView.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
//        webView.loadData(data, "text/html", "UTF -8");//API提供的标准用法，无法解决乱码问题
        webView.loadData(url, "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }

    @Override
    public String getTitleName() {
        return null;
    }


    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webView.canGoBack()) {
                webView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                finishActivityAnim();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}