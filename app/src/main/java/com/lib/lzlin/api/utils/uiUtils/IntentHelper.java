package com.lib.lzlin.api.utils.uiUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lib.lzlin.api.test.MainActivity;

/**
 * 项目名称: TaoLottery
 * <p>
 * 类的描述: 待研究 完善 TODO lz
 * 创建时间:  2017/5/22 16:56
 * 修改备注:
 */

public class IntentHelper extends Intent {
    public static final String STR_TEMP = "STR_TEMP"; // 传递字符串变量临时 key, 可自取名字
    public static final String INT_TEMP = "INT_TEMP"; // 传递int类型变量临时 key, 可自取名字
    public static final String BOOLEA_TEMP = "BOOLEA_TEMP"; // 传递boolean类型变量临时 key, 可自取名字
    public static final int DEFAULT_INT = 0; // 传递int类型变量临时 默认值
    public static final String DEFAULT_STR = "null"; // 传递int类型变量临时 默认值
    private static Context mContext;
    private static Class<? extends Activity> mCls;
    private static IntentHelper mInstance = null;

    private IntentHelper() { }

    /**
     * 获取单实例
     * @param context
     * @return
     */
    public static IntentHelper getLastInstances(Context context) {
        if (null == mInstance) { // 设置页面, 退出登录, 置空
            mInstance = getInstance(context,MainActivity.class);
        } else {
            mInstance = getInstance(context, mCls);
        }
        return mInstance;
    }

    private static IntentHelper getInstance() {
        mInstance = new IntentHelper();
        return mInstance;
    }

    private static IntentHelper getInstance(Context context, String action) {
        mContext = context;
        getInstance().setAction(action);
        return mInstance;
    }

    public static IntentHelper getInstance(Context context, Class cls) {
        mContext = context;
        mCls = cls;
        getInstance().setClass(context, cls);
        return mInstance;
    }

    /**
     * 添加参数
     * @param key       key
     * @param values    values
     * @return
     */
    public static IntentHelper put(String key, Object values) {
        if (values instanceof String) {
            return (IntentHelper) mInstance.putExtra(key, (String) values);
        } else if (values instanceof Integer) {
            return (IntentHelper) mInstance.putExtra(key, (Integer) values);
        } else if (values instanceof Boolean) {
            return (IntentHelper) mInstance.putExtra(key, (Boolean) values);
        } else if (values instanceof Float) {
            return (IntentHelper) mInstance.putExtra(key, (Float) values);
        } else if (values instanceof Double) {
            return (IntentHelper) mInstance.putExtra(key, (Double) values);
        } else if (values instanceof Long) {
            return (IntentHelper) mInstance.putExtra(key, (Long) values);
        } else {
            return (IntentHelper) mInstance.putExtra(key, values.toString());
        }
    }

    /**
     * 启动跳转, 并判断是否需要检验条件, 条件可以自定义
     * @param requireCheck  是否需要校验
     * @return              是否跳转成功
     */
    private static boolean startActivity(boolean requireCheck) {
        // TODO 根据实际情况判断
//        if (requireCheck && !OkHttpClient.isUserSign()) {
//            ToastUtils.showToast(mContext, "您还未登录, 请先登录! ");
//            mContext.startActivity(new Intent(mContext, LoginActivity.class));
//            return false;
//        }
        mInstance.setFlags(FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(mInstance);
        return true;
    }

    public static IntentHelper startActivity() {
        startActivity(false);
        return mInstance;
    }
    public static IntentHelper startActivityCheck() {
        startActivity(true);
        return mInstance;
    }

    public static void setLastCls(Class<? extends Activity> mCls) {
        IntentHelper.mCls = mCls;
    }
}
