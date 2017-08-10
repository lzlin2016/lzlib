package com.lib.lzlin.api.utils.gsonFormat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lib.lzlin.api.utils.gsonFormat.helper.GsonHelper;

import java.util.List;
import java.util.Map;

/**
 * GsonUtils1
 *
 * 类的描述: gson 解析工具类, 可实现最大容错
 * 创建人: lz -  Administrator
 * 创建时间: 2017/7/21  15:26
 * 修改人: lz -  Administrator
 * 修改备注:
 */
public class GsonUtils1 {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = GsonHelper
                    .setIsDebug(false) // 开启调试模式, 有错会直接崩溃
                    .setDefaultString("这是什么鬼, 不要给我传空值")
                    .setDefaultInteger(0)
                    .setDefaultDouble(0.00)
                    .setDefaultLong(0L)
                    .getGsonInstance();
        }
    }

    private GsonUtils1() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}
