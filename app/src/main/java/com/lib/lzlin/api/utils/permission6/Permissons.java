package com.lib.lzlin.api.utils.permission6;

import android.content.pm.PackageManager;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: Android 6.0 权限管理, 抽象类
 * 创建人: lz - Administrator
 * 创建时间:  2017/5/2 13:45
 * 修改人: lz - Administrator
 * 修改备注:
 */

public abstract class Permissons {

    /**
     * 检查是否所有的权限都被授权, 返回TRUE , 否则返回 FALSE
     * @param grantResults  权限列表
     * @return              授权情况, 是否都被授权
     */
    public static boolean verifyPermissions(int[] grantResults) {
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
