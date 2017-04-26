package com.lib.lzlin.api.js4WebView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.usepropeller.routable.Router;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 定制的与 JS 交互的 webView
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:12
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
        this.initWebview();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initWebview();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initWebview();
    }

    private void initWebview() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // web内容强制满屏
        this.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//不加上白边

        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                System.out.println(consoleMessage.toString());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        this.addJavascriptInterface(new JSMainInterface(getContext()), "XXX");  // 这是与服务端约定的协议

        this.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  特定网址, 则采用内链路由打开本地界面
                if (url.startsWith("XXX")) {
                    Router.sharedRouter().open(url);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
    }
}
