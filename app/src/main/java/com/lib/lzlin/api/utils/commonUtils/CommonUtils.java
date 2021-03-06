package com.lib.lzlin.api.utils.commonUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.Editable;
import android.view.ViewConfiguration;

import com.lib.lzlin.api.application.BaseApp;
import com.lib.lzlin.api.utils.customUtils.ToastUtils_custom;

import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 检查是否有 SD 卡, 和 toask 简单封装
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class CommonUtils {
	/**
	 * dip转化成px
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px转化成dip
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * px转化成sp
	 */
	public static int px2sp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp转化成px
	 */
	public static int sp2px(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (spValue * scale + 0.5f);
	}

	/**
	 * 获取Resource对象
	 */
	public static Resources getResources() {
		return BaseApp.getApplication().getResources();
	}

	/**
	 * 获取Drawable资源
	 */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/**
	 * 获取字符串资源
	 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**
	 * 获取color资源
	 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/**
	 * 获取dimens资源
	 */
	public static float getDimens(int resId) {
		return getResources().getDimension(resId);
	}

	/**
	 * 获取字符串数组资源
	 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/**
	 * 获取数字数组资源
	 */
	public static int[] getIntArray(int resId) {
		return getResources().getIntArray(resId);
	}

	/**
	 * 转换成标准的, 2位小数格式
	 * @param input  doubel型数据源
	 * @return  标准2位小数形式的数据
	 */
	public static String doubleFormatUtils2(String input) {
		double number = Double.parseDouble(input);
		DecimalFormat df = new DecimalFormat("0.00");
		String format = df.format(number);
		return format;
	}
	/**
	 * 转换成标准的, 2位小数格式
	 * @return  标准2位小数形式的数据
	 */
	public static String doubleFormatUtils2(double number) {
		DecimalFormat df = new DecimalFormat("0.00");
		String format = df.format(number);
		return format;
	}

	/**
	 * 格式化数据, 当数据过大时不使用科学计数法
	 * @param object
	 * @return
	 */
	public static String decimalFormat(Object object) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置
		return decimalFormat.format(object);
	}

	/**
	 * 格式化数据, 当数据过大时不使用科学计数法
	 * @param editable    编辑框
	 * @param laterInfo   错误信息提示
	 * @return
	 */
	public static void decimalFormatEditText(Context ctx, Editable editable, int length, String laterInfo) {
		String sMoney = editable.toString().trim();
		if (sMoney.contains(".") && sMoney.indexOf(".") < sMoney.length() - length - 1) {
			ToastUtils_custom.showToast(ctx, laterInfo);
			editable.delete(sMoney.length() - 1, sMoney.length());
		}
	}

	/**
	 * 判断是否存在SD卡
	 * @return
     */
	public static boolean hasSDCard(){
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		} 
		return true;
	}

	/**
	 * 获取根目录
	 * @return
	 */
	public static String getRootFilePath() {
		if (hasSDCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";// filePath:/sdcard/
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath: /data/data/
		}
	}

	/**
	 * 判断虚拟按键栏是否重写
	 *
	 * @return
	 */
	private static String getNavBarOverride() {
		String sNavBarOverride = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			try {
				Class c = Class.forName("android.os.SystemProperties");
				Method m = c.getDeclaredMethod("get", String.class);
				m.setAccessible(true);
				sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
			} catch (Throwable e) {
			}
		}
		return sNavBarOverride;
	}

	/**
	 * 检查是否存在虚拟按键栏
	 *
	 * @param context
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public static boolean hasNavBar(Context context) {
		Resources res = context.getResources();
		int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
		if (resourceId != 0) {
			boolean hasNav = res.getBoolean(resourceId);
			// check override flag
			String sNavBarOverride = getNavBarOverride();
			if ("1".equals(sNavBarOverride)) {
				hasNav = false;
			} else if ("0".equals(sNavBarOverride)) {
				hasNav = true;
			}
			return hasNav;
		} else { // fallback
			return !ViewConfiguration.get(context).hasPermanentMenuKey();
		}
	}

	/**
	 * 判断是否有虚拟按键
	 *
	 * @param context
	 * @return
	 */
	public static int getNavigationBarHeight(Context context) {
		int result = 0;
		if (hasNavBar(context)) {
			Resources res = context.getResources();
			int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = res.getDimensionPixelSize(resourceId);
			}
		}
		return result;
	}

	/**
	 * 检查网路状态
	 * @param context
	 * @return
	 */
	public static boolean checkNetState(Context context){
    	boolean netstate = false;
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						netstate = true;
						break;
					}
				}
			}
		}
		return netstate;
    }
}
