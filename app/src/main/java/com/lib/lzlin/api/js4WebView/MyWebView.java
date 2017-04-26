package com.lib.lzlin.api.js4WebView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.usepropeller.routable.Router;

/**
 * Created by Administrator on 2017/3/8.
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
        this.setWebview();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWebview();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWebview();
    }

    private void setWebview() {
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

        this.addJavascriptInterface(new JSMainInterface(getContext()), "iBHLYZX");

        this.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("url tostring", url);
                if (url.startsWith("rapaq://")) {
                    Router.sharedRouter().open(url);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
    }
}
