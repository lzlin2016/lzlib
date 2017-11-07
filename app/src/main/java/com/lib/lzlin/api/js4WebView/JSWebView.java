package com.lib.lzlin.api.js4WebView;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 项目名称: MyApplication
 * <p>
 * 类的描述: 定制 WebView
 * 创建人: lz - Administrator
 * 创建时间:  2017/5/15 14:19
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class JSWebView extends BridgeWebView {
    private final static String INIT = "init";
    private final static String GETUSERINFO = "getUserInfo";
    private final static String BEGINASYNCAJAX = "beginAsyncAjax";
    private final static String OPENDIALOG = "openDialog";
    private final static String LOGOUT = "logout";
    private final static String JUMPTOPAGE = "jumpToPage";
    private final static String TOAST = "toast";
    private final static String SAVEIMAGE = "saveImage";
    private Handler mHandler = new Handler();

    private Context context;

    public JSWebView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public JSWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public JSWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    protected void init() {
        this.setDefaultHandler(new DefaultHandler());
        this.setWebChromeClient(new WebChromeClient() { });
        this.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.registerHandler(INIT);
        this.send("");
    }

    /**
     * 动态注册，提供给 webview 调用
     *
     * @param method 需要注册的方法名称
     * @return 返回 MyWebView 对象本身
     */
    protected JSWebView registerHandler(final String method) {
        this.registerHandler(method, new BridgeHandler() {
            @Override
            public void handler(String data, final CallBackFunction function) {
                if (method == null) { // 判空
                    return;
                }
                if (data == null) { // 判空
                    data = "";
                }
                if (method.equalsIgnoreCase(GETUSERINFO)) {
                    getUserInfo(function);
                } else if (method.equalsIgnoreCase(BEGINASYNCAJAX)) {
                    beginAsyncAjax(data, function);
                } else if (method.equalsIgnoreCase(OPENDIALOG)) {
                    openDialog(data);
                } else if (method.equalsIgnoreCase(LOGOUT)) {
                    logout();
                } else if (method.equalsIgnoreCase(INIT)) {
                    jsInit(method, data, function);
                } else if (method.equalsIgnoreCase(JUMPTOPAGE)) {// 跳转界面
                    toStart(data);
                } else if (method.equalsIgnoreCase(TOAST)) {
//                    ToastUtils_custom.showShort(context, data);
                }else if(method.equalsIgnoreCase(SAVEIMAGE)){// 保存图片到图库
                    savaImage(data);
                }
            }
        });
        return this;
    }

    private void savaImage(final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                final Bitmap bitmap = GlideUtils.getBitmapByUrl(context, imageUrl, 576, 576);
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (bitmap == null) {
//                            ToastUtils_custom.showShort(context, "二维码图片路径错误");
//                            return;
//                        }
//                        GalleryUtils.saveImageToGallery(context, bitmap);
//                        ToastUtils_custom.showShort(context, "二维码保存成功, 你可以在相册查看! ");
//                    }
//                });
            }
        }) .start();
    }

    private void toStart(String data) {
//        Intent intent = new Intent(context, EventDetailActivity.class);
//        String url = "";
//        String title = "";
//        try {
//            JSONObject jsonObject = new JSONObject(data);
//            url = (String) jsonObject.getString("url");
//            title = (String) jsonObject.getString("title");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////                    Log.e("registerHandler","url=="+url+"title=="+title);
//        intent.putExtra("url", url);
//        intent.putExtra("title", title);
//        context.startActivity(intent);
    }

    private void jsInit(String method, String data, CallBackFunction function) {
        if (method.equalsIgnoreCase(INIT)) { // 封装, 初始化
            for (String meathod : builMethod(data)) {
                JSWebView.this.registerHandler(meathod);
            }
            function.onCallBack(new JSONObject().toString());
        }
    }

    private void logout() {
//        // 置空token, 下次启动跳转登录界面
//        SPUtils.remove(TaoConstants.Key.SP_USERID);
//        SPUtils.remove(TaoConstants.Key.SP_TOKEN);
//        EventBus.getDefault().post(new SigninEvent());
    }

    private void beginAsyncAjax(String data, final CallBackFunction function) {
        JSONObject jsonObject = null;
        JSONObject object = null;
        try {
            jsonObject = new JSONObject(data);
            object = jsonObject.getJSONObject("data");
//            HashMap<String, String> hashMap = MyCustomUtils.buildParams(object.toString());
//            openDialog("true");
//            Log.e("beginAsyncAjax","beginAsyncAjax=="+data);
//            OkGo.post(TaoConstants.Url.HOST + (String) jsonObject.get("url"))//
//                    .tag(this)//
//                    .params(hashMap)
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String s, Call call, Response response) {
//                            if (function != null && s != null) {
//                                function.onCallBack(s);
//                            }
//                            Log.e("onSuccess", "onSuccess==" + s);
//                        }
//                    });
//            Log.e("beginAsyncAjax", "object==" + TaoConstants.Url.HOST + (String) jsonObject.get("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo(CallBackFunction function) {
//        User user = new User((String) SPUtils.get(TaoConstants.Key.SP_USERID, ""), (String) SPUtils.get(TaoConstants.Key.SP_TOKEN, ""));
//        String gsonString = GsonUtils.GsonString(user);
//        function.onCallBack(gsonString);
        function.onCallBack("gsonString");
    }

    private Dialog progressDialog;

    public void openDialog(String data) {
        if (progressDialog == null) {
            progressDialog = new Dialog(context);
        }
        if (data.equalsIgnoreCase("true")) {
            //网络请求前显示对话框
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.show();
            }
        } else {
            //网络请求结束后关闭对话框
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

    /**
     * 构造hasMap用于post 请求参数
     * @param info
     * @return
     */
    public static HashMap<String, String> buildParams(String info) {
        HashMap<String, String> map = new HashMap<>();
        info = info.trim();
        if (null == info || "{}".equals(info) || "".equals(info)) {
            return map;
        }
        info = info.replace("}", "").replace("{", "").replace("\"", "");
        String[] split1 = info.split(",");
        for (String param: split1) {
            String[] split2 = param.split(":");
            if (split2.length == 2) {
                map.put(split2[0], split2[1]);
            }
        }
        return map;
    }

    /**
     * 构造 ArrayList 用于 webView registerHandler 参数
     * @param info
     * @return
     */
    public static ArrayList<String> builMethod (String info) {
        ArrayList<String> methods  = new ArrayList<>();
        info = info.trim();
        if (null == info || "[]".equals(info) || "".equals(info)) {
            return methods;
        }
        info = info.replace("[", "").replace("]", "").replace("\"", "");
        String[] split = info.split(",");
        for (String method: split) {
            methods.add(method);
        }
        return methods;
    }

}
