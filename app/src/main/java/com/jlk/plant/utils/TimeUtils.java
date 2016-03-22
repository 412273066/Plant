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

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置时区否则获得的时间少8小时
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Long time = new Long(timestamp * 1000L);

        Date date = new Date(time);

        String str = sdf.format(date);

        return str;
    }
}
