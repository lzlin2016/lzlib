package com.lib.lzlin.api.utils.activityUtils;

import android.content.Context;
import android.content.Intent;

import com.lib.lzlin.api.test.MainActivity;

/**
 * 项目名称: TaoLottery
 * <p>
 * 类的描述: 有错, 待研究 TODO lz
 * 创建人: lz - Administrator
 * 创建时间:  2017/5/22 16:56
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class IntentHelper extends Intent {
    private static Context mContext;
    private static IntentHelper mInstance = null;

    private IntentHelper() { }

    /**
     * 获取单实例
     * @param context
     * @return
     */
    public static IntentHelper getLastInstances(Context context) {
        mContext = context;
        return mInstance == null ? (IntentHelper) new IntentHelper().setClass(mContext, MainActivity.class) : mInstance;
    }

    private static IntentHelper getInstance(Context context) {
        mContext = context;
        mInstance = mInstance == null ? new IntentHelper() : mInstance;
        return mInstance;
    }

    public static IntentHelper getInstance(Context context, String action) {
        getInstance(context).setAction(action);
        return mInstance;
    }

    public static IntentHelper getInstance(Context context, Class cls) {
        getInstance(context).setClass(context, cls);
        return mInstance;
    }

    /**
     * 添加参数
     * @param key       key
     * @param values    values
     * @return
     */
    public  static IntentHelper putExtraValus(String key,  String values) {
        return (IntentHelper) mInstance.putExtra(key, values);
    }

    /**
     * 启动跳转, 并判断是否需要检验条件, 条件可以自定义
     * @param requireCheck  是否需要校验
     * @return              是否跳转成功
     */
    private static boolean startActivity(boolean requireCheck) {
        if (requireCheck) {
//            String token = (String) SPUtils.get(TaoConstants.Key.SP_TOKEN, "");
//            if (token == null || "".equals(token)) {
//                // TODO lz 改成不满足条件需要跳转到额目标界面
//                mContext.startActivity(new Intent(mContext, SignInActivity.class));
//                return false;
//            }
        }
        mContext.startActivity(mInstance);
        mInstance = null;
        return true;
    }

    public static void startActivity() {
        startActivity(false);
    }
    public static void startActivityCheck() {
        startActivity(true);
    }

}
