package com.lib.lzlin.api.utils.customUtils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import static com.lib.lzlin.api.utils.commonUtils.CommonUtil.getResources;

/**
 * 项目名称: HappyGo
 * <p>
 * 类的描述: 为本项目特别定制工具类
 * 创建时间:  2017/7/25 9:48
 * 修改备注:
 */

public class CustomUtils {
    /**
     * 获取在android项目的res文件夹下面的图片
     * @param context   上下文对象
     * @param imgName   图片 名称
     * @return
     */
    public static int getResImgId(Context context, String imgName) {
        // 得到application对象
        ApplicationInfo appInfo = context.getApplicationInfo();
        //得到该图片的id(name 是该图片的名字，"drawable" 是该图片存放的目录，appInfo.packageName是包名)
        int resID = getResources().getIdentifier(imgName, "drawable", appInfo.packageName);
        return resID;
    }

    /**
     * 计算listView 实际高度, 需要传入listView 对象
     * @param listView  listView 对象
     * @return
     */
    public static int getListViewHeightBasedOnChildren(ListView listView) {
        int totalHeight = 0;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return totalHeight;
        }
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View view = listAdapter.getView(i, listView.getChildAt(i), listView);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }
        totalHeight += (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        return totalHeight;
    }
}
