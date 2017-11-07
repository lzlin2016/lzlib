package com.lib.lzlin.api.utils.deviceUtils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.JsonObject;

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
     * android 版本的,  Note: 6.0以上, 安全权限问题, 使用之前必须先动态申请权限
     * @param activity activity对象
     * @return UUID
     */
    private static String getMyUUID(Activity activity){
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

    /**
     * 备用获取手机唯一id
     * 一种比较折中的办法，在获取MAC地址之前先判断当前WiFi状态，若开启了Wifi，则直接获取MAC地址，若没开启Wifi，则用代码开启Wifi，然后马上关闭，再获取MAC地址。
     * 目前此方法测试成功，无论在哪种状态下都能正确取得设备的MAC地址（包括开机后未启动过Wifi的状态下），且在未开启Wifi的状态下，用代码开启Wifi并马上关闭，
     * 过程极短，不会影响到用户操作。
     */
    public static String getMacAddress(Context context) {

        String macAddress = null;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if (null != info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }



    public static String getDeviceInfo(Context context) {
        JsonObject json = new JsonObject();
        TelephonyManager manage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        return manage.getDeviceId();
    }

}
