package com.lib.lzlin.api.utils.commonUtils;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.UUID;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:  UUID , 产生唯一 UUID, 可用于订单号等
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 15:45
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class UUIDUtils {
    /**
     * android 版本的
     * @param activity activity对象
     * @return UUID
     */
    public static String getMyUUID(Activity activity){
        final TelephonyManager tm = (TelephonyManager) activity.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Settings.Secure.getString(activity.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        Log.d("debug","uuid="+uniqueId);
        return uniqueId;
    }

    /**
     * java 版本的
     * @return UUID
     */
    public static String getMyUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        Log.e("getMyUUID","----->UUID: "+uuid);
        return uniqueId;
    }

    /**
     * java 版本的
     * @return UUID
     */
    public static String getMyUUID2OrderId(){
        String uniqueId = getMyUUID().replace("-", "");
        Log.e("getMyUUID2OrderId","----->UUID: "+uniqueId);
        Log.e("getMyUUID2OrderId", "uniqueId.leng = " + uniqueId.length());
        return uniqueId;
    }
}
