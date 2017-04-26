package com.lib.lzlin.api.utils.commonUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 常见格式转化, double 类型的位数, 以及时间与时间戳相互转换
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class DecimalFormatUtils {
    /**
     * 转换成标准的, 2位小数格式
     * @param input  doubel型数据源
     * @param weishu 小数点后面保留几位
     * @return  标准2位小数形式的数据
     */
    public static String doubleFormatUtils(double input, int weishu) {
        StringBuffer sb = new StringBuffer();
        sb.append("0");
        if (weishu > 0) {
            sb.append(".");
            for (int i = 0; i < weishu; i++) {
                sb.append(0);
            }
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        String format = df.format(input);
        return format;
    }

    /**
     * 转换成标准的, 2位小数格式
     * @param input  doubel型数据源
     * @return  标准2位小数形式的数据
     */
    public static String doubleFormatUtils2(double input) {
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(input);
        return format;
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
     * 转换成标准的, 2位小数格式, 返回小数
     * @param input  doubel型数据源
     * @return  标准2位小数形式的数据
     */
    public static double doubleFormatUtils2Doubel(String input) {
        double number = Double.parseDouble(input);
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(number);
        return Double.parseDouble(format);
    }

    /**
     * 将时间转换为时间戳
     * @param data 北京时间
     * @return  时间戳
     * @throws ParseException
     */
    public static String dateToStamp(String data) throws ParseException {
        String stamp;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(data);
        long ts = date.getTime();
        stamp = String.valueOf(ts);
        return stamp;
    }

    /**
     * 将时间戳转换为时间
     * @param stamp 时间戳
     * @return  北京时间
     */
    public static String stampToDate(String stamp){
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(stamp);
        Date date = new Date(lt * 1000L);
        time = simpleDateFormat.format(date);
        return time;
    }
}
