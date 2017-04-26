package com.lib.lzlin.api.utils.deviceUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import com.lib.lzlin.api.utils.commonUtils.GalleryUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 截屏工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class ScreenShotUtils {

    private static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    private static boolean savePic(Bitmap b, File filePath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 截屏
     * @param a activity对象
     * @param filePath  路径
     * @return  操作是否成功
     */
    public static boolean shoot(Activity a, File filePath) {
        if (filePath == null) {
            return false;
        }
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        ScreenShotUtils.savePic(ScreenShotUtils.takeScreenShot(a), filePath);
        return true;
    }

    /**
     * 截屏
     * @param activity activity对象
     * @return  操作是否成功
     */
    public static boolean shoot(Activity activity) {
        Bitmap bitmap = ScreenShotUtils.takeScreenShot(activity);  // 此处只需要上下文对象即可
        if (bitmap == null) {
            return false;
        }
        GalleryUtils.saveImageToGallery(activity,bitmap);
        return true;
    }
}