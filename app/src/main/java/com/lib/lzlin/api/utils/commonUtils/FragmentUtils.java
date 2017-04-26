package com.lib.lzlin.api.utils.commonUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 片段替换, 辅助工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.lib.lzlin.api.R;

public class FragmentUtils {
    /**
     * 替换片段
     * @param activity  对象
     * @param fragment  所替换的片段
     */
    public static void replace (FragmentActivity activity, Fragment fragment) {
        if(fragment!=null){
            FragmentTransaction replace = activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment);
            replace.commitAllowingStateLoss( ); // TODO lz 解决IllegalStateException非法状态异常
//            replace.commit(); // 优化
        }
    }

    /**
     * 替换片段
     * @param activity  对象
     * @param containerId  要替换的 fragment ID
     * @param fragment  所替换的片段
     */
    public static void replace (FragmentActivity activity, int containerId, Fragment fragment) {
        if(fragment!=null){
            FragmentTransaction replace = activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment); // R.id.container
            replace.commitAllowingStateLoss( ); // TODO lz 解决IllegalStateException非法状态异常
//            replace.commit(); // 优化
        }
    }

    /**
     * 替换片段
     * @param activity  对象
     * @param containerId  被替换的片段
     * @param fragment  所替换的片段
     */
    public static void replaceByContainerId (FragmentActivity activity, int containerId, Fragment fragment) {
        if(fragment!=null){
            FragmentTransaction replace = activity.getSupportFragmentManager().beginTransaction().replace(containerId, fragment);
            replace.commitAllowingStateLoss( ); // TODO lz 解决IllegalStateException非法状态异常
        }
    }

    /**
     * 替换片段
     * @param fagment  片段对象
     * @param containerId  被替换的片段
     * @param fragment  所替换的片段
     */
    public static void replaceByContainerId4Fragment (Fragment fagment, int containerId, Fragment fragment) {
        if(fragment!=null){
            FragmentTransaction replace = fagment.getChildFragmentManager().beginTransaction().replace(containerId, fragment);
            replace.commitAllowingStateLoss( ); // TODO lz 解决IllegalStateException非法状态异常
        }
    }

}

