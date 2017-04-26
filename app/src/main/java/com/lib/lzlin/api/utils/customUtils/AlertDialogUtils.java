package com.lib.lzlin.api.utils.customUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: AlertDialogUtils 对话框 封装
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class AlertDialogUtils {
	public static AlertDialog alertDialog;
	public static Window window;
	
	private AlertDialogUtils(){
		
	}
	
	/**
	 * 未设置动画
	 * @param context
	 * @param rid
	 * @return
	 */
	public static AlertDialog getInstance(Context context , int rid) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setContentView(rid);
		window = alertDialog.getWindow();
		return alertDialog;
	}
	
	/**
	 * 未设置动画
	 * @param context
	 * @param view
	 * @return
	 */
	public static AlertDialog getInstance(Context context , View view) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.show();
		alertDialog.setContentView(view);
		window = alertDialog.getWindow();
		return alertDialog;
	}
	
	/**
	 * 设置动画
	 * @param context
	 * @param rid
	 * @return
	 */
	public static AlertDialog getInstance(Context context , int rid, int animid) {
		alertDialog = new AlertDialog.Builder(context).create();
		if(alertDialog!=null){
			alertDialog.show();			
			alertDialog.setContentView(rid);
			window = alertDialog.getWindow();
			window.setWindowAnimations(animid);
		}
		return alertDialog;
	}
	
	public static Window getWindow() {
		if(window!=null){
			return window;
		}
		return null;
	}

}
