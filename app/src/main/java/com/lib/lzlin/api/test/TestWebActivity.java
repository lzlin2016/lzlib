package com.lib.lzlin.api.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lib.lzlin.api.R;
import com.lib.lzlin.api.js4WebView.MyWebView;

public class TestWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web);

        // 初始化UI
        initUI();
    }

    private void initUI() {
        MyWebView mWebView = (MyWebView) findViewById(R.id.webview);
        mWebView.loadUrl("http://web_deploy.vhost.ibenhong.com/LuoBaiKu/member/indent-list.html");
    }
}
