package com.lib.lzlin.api.utils.netUtils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 网络连接, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ConnectionUtils {
	/**
	 * wifi 连接
	 * @param context
	 * @return
	 */
	public static boolean isWIFI(Context context){
		if (isConnected(context))
		{
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			int type = info.getType();
			if (ConnectivityManager.TYPE_WIFI == type)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 手机数据连接
	 * @param context
	 * @return
	 */
	public static boolean isMobile(Context context){
		if (isConnected(context))
		{
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			int type = info.getType();
			if (ConnectivityManager.TYPE_MOBILE == type)
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isConnected(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null){
			return false;
		}
		boolean available = info.isAvailable();
		return available;
	}

	public  static void openNet(Context context) {
		if (Build.VERSION.SDK_INT > 13) {
			// 3.2以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
			context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
		} else {
			context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}
	}
}
