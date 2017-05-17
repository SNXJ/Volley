package com.snxj.volley.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */
public class MyDateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat dateFormatYMD = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 将long得到-- 小时:分
     *
     * @param lefttime
     * @return 小时:分
     */
    public static String formatTimeSimple(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return sdf.format(lefttime);
    }

    public static String formatTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return sdf.format(lefttime);
    }

    /**
     * 得到: 年-月-日 小时:分钟
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟
     */
    public static String formatDateAndTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);

        return sdf.format(lefttime);
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDate(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(lefttime);
    }

    /**
     * 字符串转为long
     *
     * @param time     字符串时间,注意:格式要与template定义的一样
     * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
     * @return long
     */
    public static long formatToLong(String time, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            Date d = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到年份
     *
     * @param lefttime
     * @return 得到年份
     */
    public static int formatYear(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return Integer.parseInt(sDateTime);
    }

    /**
     * 得到月份
     *
     * @param lefttime
     * @return 得到月份
     */
    public static int formatMonth(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return Integer.parseInt(sDateTime);
    }

    /**
     * 取得当前的时间，格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
//    public static String getCurDateTimeStr() {
//        return dateFormat.format(new Date());
//    }

    /**
     * 取得的时间串，格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
//    public static String getDateTimeStr(Date date) {
//        if (date == null) {
//            return getCurDateTimeStr();
//        }
//        return dateFormat.format(date);
//    }

    /**
     * 取得的日期串，格式为 yyyy-MM-dd
     *
     * @return
     */
//    public static String getDateYMDStr(Date date) {
//        if (date == null) {
//            return getCurDateTimeStr();
//        }
//        return dateFormatYMD.format(date);
//    }

    public static Date getYDMStrToDate(String formatStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(formatStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateStrByFormat(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        if (date == null) {
            date = new Date();
        }
        if (formatStr == null) {
            format = dateFormat;
        }
        return format.format(date);
    }

    public static String getTimes(long times) {
        // if(null==date){
        // return "";
        // }
        // Calendar cl = Calendar.getInstance();//当前时间
        // long times = cl.getTimeInMillis()-date.getTime();
        String value = "";
        // 计算出相差天数
        double days = Math.floor(times / (double)(24 * 3600 * 1000));
        // 计算出小时数
        long leave1 = times % (24 * 3600 * 1000); // 计算天数后剩余的毫秒数
        double hours = Math.floor(leave1 /(double) (3600 * 1000));
        // 计算相差分钟数
        long leave2 = leave1 % (3600 * 1000); // 计算小时数后剩余的毫秒数
        double minutes = Math.floor(leave2 /(double) (60 * 1000));
        // 计算相差秒数
        double leave3 = leave2 % (60 * 1000); // 计算分钟数后剩余的毫秒数
        double seconds = Math.round(leave3 / 1000);
        if (days > 3) {
            value = formatDate(times);
        } else if (days <= 3 && days > 0) {
            value = (int) days + "天前";
        } else {
            if (hours > 0) {
                value = (int) hours + "小时前";
            } else {
                if (minutes > 0) {
                    value = (int) minutes + "分钟前";
                } else {
                    value = (int) seconds + "秒前";
                }
            }
        }
        return value;
    }

}
