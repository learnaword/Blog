package com.mjl.blog.common.utils;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 */
public class DateUtils {

    /**
     * 时区 - 默认
     */
    public static final String TIME_ZONE_DEFAULT = "GMT+8";

    /**
     * 秒转换成毫秒
     */
    public static final long SECOND_MILLIS = 1000;

    public static final String FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";

    /**
     * 将 LocalDateTime 转换成 Date
     *
     * @param date LocalDateTime
     * @return LocalDateTime
     */
    public static Date of(LocalDateTime date) {
        // 将此日期时间与时区相结合以创建 ZonedDateTime
        ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());
        // 本地时间线 LocalDateTime 到即时时间线 Instant 时间戳
        Instant instant = zonedDateTime.toInstant();
        // UTC时间(世界协调时间,UTC + 00:00)转北京(北京,UTC + 8:00)时间
        return Date.from(instant);
    }

    /**
     * 将 Date 转换成 LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime of(Date date) {
        // 转为时间戳
        Instant instant = date.toInstant();
        // UTC时间(世界协调时间,UTC + 00:00)转北京(北京,UTC + 8:00)时间
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date addTime(Duration duration) {
        return new Date(System.currentTimeMillis() + duration.toMillis());
    }

    public static boolean isExpired(Date time) {
        return System.currentTimeMillis() > time.getTime();
    }

    public static boolean isExpired(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(time);
    }

    public static long diff(Date endTime, Date startTime) {
        return endTime.getTime() - startTime.getTime();
    }

    /**
     * 创建指定时间
     *
     * @param year  年
     * @param mouth 月
     * @param day   日
     * @return 指定时间
     */
    public static Date buildTime(int year, int mouth, int day) {
        return buildTime(year, mouth, day, 0, 0, 0);
    }

    /**
     * 创建指定时间
     *
     * @param year   年
     * @param mouth  月
     * @param day    日
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     * @return 指定时间
     */
    public static Date buildTime(int year, int mouth, int day,
                                 int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, mouth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0); // 一般情况下，都是 0 毫秒
        return calendar.getTime();
    }

    public static Date max(Date a, Date b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) > 0 ? a : b;
    }

    public static LocalDateTime max(LocalDateTime a, LocalDateTime b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.isAfter(b) ? a : b;
    }

    public static String timestampToString(Long timestamp){
        Date date = new Date(timestamp); // 需要将秒数转换为毫秒数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 计算当期时间相差的日期
     *
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(int field, int amount) {
        return addDate(null, field, amount);
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param date   设置时间
     * @param field  日历字段 例如说，{@link Calendar#DAY_OF_MONTH} 等
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(Date date, int field, int amount) {
        if (amount == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 是否今天
     *
     * @param date 日期
     * @return 是否
     */
    public static boolean isToday(LocalDateTime date) {
        return LocalDateTimeUtil.isSameDay(date, LocalDateTime.now());
    }

    /**
     *
     *两个时间戳相差的天数
     * @return 是否
     */
    public static Long diff(Long start,Long end) {
        return TimeUnit.MILLISECONDS.toDays(end - start);
    }

    public static Boolean isMidnight() {
        // 获取当前时间（中国时区）
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 判断当前时间是否在午夜附近（在 00:00 到 03:00 之间）
        return currentDateTime.toLocalTime().isAfter(LocalTime.MIDNIGHT) && currentDateTime.toLocalTime().isBefore(LocalTime.of(3, 0));
    }

}
