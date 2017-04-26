package com.lib.lzlin.api.utils.activityUtils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 实现Activity 跳转中间过程, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ActivityHelper {
    // TODO lz 根据需要添加
    public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE"; // 备用, fragment type
    public static final String LAYOUT_ID = "LAYOUT_ID"; // 备用, 布局 ID
    public static final String INDEX = "INDEX"; // 备用 下标索引

    /**
     * 实现跳转普通不带参数的 Activity, 辅助工具
     * @param context       上下文对象
     * @param clz           目标Activity
     */
    public static void startActivity(Context context, Class clz) {
        startActivity(context, clz, new KeyVaule[]{});
    }

    /**
     * 实现跳转普通带指定 fragment 类型参数 Fragment, 辅助工具
     * @param context       上下文对象
     * @param clz           目标Activity
     */
    public static void startFragment(Context context, Class clz, int fragmentType) {
        startActivity(context, clz, new KeyVaule(FRAGMENT_TYPE, fragmentType));
    }

    /**
     * 实现跳转带指定 fragment 类型 和 带 layoutID 参数的 Fragment, 辅助工具
     * @param context       上下文对象
     * @param clz           目标Activity
     */
    public static void startFragment(Context context, Class clz, int fragmentType, int layoutID) {
        KeyVaule keyVauleFragmentType = new KeyVaule(FRAGMENT_TYPE, fragmentType);
        KeyVaule keyVauleLayoutId = new KeyVaule(LAYOUT_ID, layoutID);
        startActivity(context, clz, new KeyVaule[]{keyVauleFragmentType, keyVauleLayoutId});
    }

    /**
     * 实现跳转带指定 fragment 类型 和 带 layoutID 参数的 Fragment, 辅助工具
     * @param context       上下文对象
     * @param clz           目标Activity
     */
    public static void startFragment(Context context, Class clz, int fragmentType, int layoutID, int index) {
        KeyVaule keyVauleFragmentType = new KeyVaule(FRAGMENT_TYPE, fragmentType);
        KeyVaule keyVauleLayoutId = new KeyVaule(LAYOUT_ID, layoutID);
        KeyVaule keyVauleIndex = new KeyVaule(INDEX, index);
        startActivity(context, clz, new KeyVaule[]{keyVauleFragmentType, keyVauleLayoutId, keyVauleIndex});
    }

    /**
     * 实现跳转带参数的 Activity, 辅助工具
     * @param context       上下文对象
     * @param clz           目标Activity
     * @param extraVaules   附带值
     */
    public static void startActivity(Context context, Class clz, KeyVaule... extraVaules) {
        Intent intent = new Intent(context, clz);
        // 解决在非Activity中使用startActivity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (extraVaules != null) { // 判空, 没有数据则不需要{
              for (KeyVaule extra: extraVaules) {
            //  警告: 最后一个参数使用了不准确的变量类型的 varargs 方法的非 varargs 调用;
//            for (int i = 0; i < extraVaules.length; i ++ ) {
//                KeyVaule extra = extraVaules[i];
                if (extra.value instanceof String) {
                    intent.putExtra(extra.key,  ((String) extra.value).trim() );
                    Log.i("startActivity","String : " + extra.value);
                } else if (extra.value instanceof Integer) {
                    intent.putExtra(extra.key,  (Integer)extra.value );
                    Log.i("startActivity","Integer : " + extra.value);
                } else if (extra.value instanceof Double) {
                    intent.putExtra(extra.key,  (Double) extra.value );
                    Log.i("startActivity","Double : " + extra.value);
                } else if (extra.value instanceof Float) {
                    intent.putExtra(extra.key,  (Float) extra.value );
                    Log.i("startActivity","Float : " + extra.value);
                } else if (extra.value instanceof Boolean) {
                    intent.putExtra(extra.key,  (Boolean)extra.value );
                    Log.i("startActivity","Boolean : " + extra.value);
                } else {
                    throw new RuntimeException(
                            "The extraVaules unknowed " + extra.value.toString());
                }
            }
        }
        context.startActivity(intent);
    }

}
