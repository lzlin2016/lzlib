package com.lib.lzlin.api.utils.netUtils;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: HTTPUtils, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class HTTPUtils {
    private static RequestQueue mRequestQueue;
    private static final boolean USE_OKHTTP = false;

    private HTTPUtils() {
    }

    private static void init(Context context) {
        if (USE_OKHTTP) {
//			OkHttpClient client = new OkHttpClient().newBuilder().build();
//			mRequestQueue = Volley.newRequestQueue(context, new OkHttp3Stack(client));
        } else {
            // 使用默认的HTTPURLConnection
            // 2.3之前HTTPClient
            mRequestQueue = Volley.newRequestQueue(context);
        }

    }

    public static void post(final Context context, String url, final Map<String, String> params,
                            final ResponseListener listener) {
        StringRequest myReq = new UTFStringRequest(Method.POST, url, listener, listener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        if (mRequestQueue == null) {
            init(context);
        }

        // 请用缓存
        myReq.setShouldCache(true);
        // 设置缓存时间10分钟
        // myReq.setCacheTime(10*60);
        mRequestQueue.add(myReq);
    }

    public static void get(Context context, String url, Map<String, String> params, final ResponseListener listener) {
        url = buildParams(url, params);
        get(context, url, listener);
    }

    public static String buildParams(String url, Map<String, String> params) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        if (params == null || params.size() == 0) {
            return url;
        }
        url += "?";
        Set<String> keySet = params.keySet();
        Iterator<String> itr = keySet.iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            url += key + "=" + params.get(key) + "&";
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }

    public static void get(Context context, String url, final ResponseListener listener) {
        // 判断当前如果没有网络，则返回null
        if (!ConnectionUtils.isConnected(context)) {
            VolleyError error = new VolleyError("no connection");
            listener.onErrorResponse(error);
            return;
        }

        StringRequest myReq = new UTFStringRequest(Method.GET, url, listener, listener);
        if (mRequestQueue == null) {
            init(context);
        }
        mRequestQueue.add(myReq);
    }

    private static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void cancelAll(Context context) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(context);
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache}
     * which effectively means that no memory caching is used. This is useful
     * for images that you know that will be show only once.
     *
     * @return
     */
    // baseCommon static ImageLoader getImageLoader() {
    // if (mImageLoader != null) {
    // return mImageLoader;
    // } else {
    // throw new IllegalStateException("ImageLoader not initialized");
    // }
    // }
    public static class UTFStringRequest extends StringRequest {

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data, "utf-8");
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }

        public UTFStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

    }

    /**
     * 解析Unicode
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
//        // TODO lz　标注：解决 null 空指针问题
//        String decode = outBuffer.toString().replace("null", "\"" + YGBConstant.helpSoftNullProblem.repalceNullValue + "\"");
        return outBuffer.toString();
    }
}
