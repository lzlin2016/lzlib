package com.lib.lzlin.api.utils.andfixUtils;

import android.app.Activity;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述:  热更新
 * 创建时间:  2017/8/31 14:39
 * 修改备注:  参考资源: http://www.jianshu.com/p/4f11baacd5a3
 *      1. 需要依赖:  compile 'com.alipay.euler:andfix:0.4.0@aar'
 *      2. 需要先下载热更新文件, 文件生成方式参考资源
 *          生成: apkpatch -f new.apk  -t old.apk -o ./  -k ./package.jks -p ibenhong2015 -a benhong -e ibenhong2015
 *      3. 调用工具类实现热更新
 */

public class AndfixUtils {
    /**
     * 初始化热更新
     */
    public static void andFix(Activity mActivity, String filePath) {
        PatchManager mPatchManager = new PatchManager(mActivity);
        String appversion = "-1"; // 默认版本-1
        try {
            appversion = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mPatchManager.init(appversion);// current version
        mPatchManager.loadPatch();
        try {
            mPatchManager.addPatch(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
