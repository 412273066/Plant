package com.jlk.plant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 将字符串转为时间戳
     *
     * @param str
     * @return
     */

    public static long getTime(String str) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        long timestamp = 0;
        try {

            date = sdf.parse(str);
            timestamp = date.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 不足两位前面补0,用于时间选择器
     *
     * @param num
     * @return
     */
    public static String appendZero(String num) {
        if (num.length() < 2) {
            num = "0" + num;
        }
        return num;
    }

    /**
     * 获取当期时间日期
     *
     * @return
     */
    public static String getTime() {
        Calendar localCalendar = Calendar.getInstance();
        int month = localCalendar.get(Calendar.MONTH) + 1;
        int dateOfMonth = localCalendar.get(Calendar.DATE);
        int minute = localCalendar.get(Calendar.MINUTE);
        int hour = localCalendar.get(Calendar.HOUR);

        return month + "月" + dateOfMonth + "日 " + appendZero(hour + "") + ":"
                + appendZero(minute + "");
    }

    /**
     * 时间戳转换成日期
     *
     * @param timestamp
     * @return
     */
    public static String timestamp2date(long timestamp) {
        String seconds = String.valueOf(timestamp);
        return timeStamp2Date(seconds, null);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        //设置时区否则获得的时间少8小时
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Date date = new Date(Long.valueOf(seconds + "000"));

        return sdf.format(date);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
