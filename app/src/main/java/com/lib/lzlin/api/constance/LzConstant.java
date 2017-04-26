package com.lib.lzlin.api.constance;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 常量池
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:12
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class LzConstant {
    /**
     * 用于打开和关闭登陆开关
     */
    public static final class OpenSwitch2Client {
        public static final boolean isOpenLogin = true; // 是否打开, 是否可以免登陆
        public static final boolean isOpenStatebarColor = false; // 是否打开, 是否使用渲染 / 沉浸式  状态栏
    }

    /**
     * 存放一些常亮
     */
    public  static final class Constans {
        // 公共参数common
        public static final String TYPE = "type"; // Fragment 唯一标识
        public static final int DEFAULT_TYPE = 0; // 默认标识
    }

    /**
     * 定义一些数据请求的URL 常量
     */
    public static final class Url4AppClient {
        public static final String BASE_URL = "你们公司的域名";// 主网址
        // 不同类型的URL接口地址
        public static final String userSignInAuthor = "详细接口";// 用户登陆
    }
}
