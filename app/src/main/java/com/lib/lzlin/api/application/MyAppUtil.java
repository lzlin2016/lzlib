package com.lib.lzlin.api.application;

import android.app.Activity;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: application 基类辅助工具
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:11
 * 修改人: lz - Administrator
 * 修改备注:
 */
public class MyAppUtil {
	public static void addActivity(Activity activity) {
		BaseApp application = (BaseApp) activity.getApplication();
		application.addActivty(activity);
	}
	
	public static void deleteActivity(Activity activity) {
		BaseApp application = (BaseApp) activity.getApplication();
		application.deleteActivity(activity);
	}

	public static void exit(Activity activity) {
		BaseApp application = (BaseApp) activity.getApplication();
		application.exit();
	}
}
