package com.lib.lzlin.api.utils.commonUtils;


import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 时间日期工具类
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class DateUtils
{
    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     * 
     * @return
     */
    public static String getTimeShort(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date(123);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取制定日期的时间 小时:分 HH:mm
     * 
     * @return
     */
    public static String getDateOfTime(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @param mill
     * @return
     */
    public static String convert(String mill)
    {
        if (mill == null || mill.equals("null") || mill.equals(""))
        {
            return "";
        }
        return String.format("%tF", Long.valueOf(mill) * 1000);
        // return String.format("%tF %<tT", mill);
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 转换为字符串 yyyy-MM-dd
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            天
     * @return
     */
    public static String intFormatToDateStr(int year, int month, int day)
    {
        String parten = "00";
        DecimalFormat decimal = new DecimalFormat(parten);
        String dateStr = year + "-" + decimal.format(month) + "-"
                + decimal.format(day);
        return dateStr;
    }

}
