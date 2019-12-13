package com.likai.gateway.Util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtil {

    private static Locale locale = Locale.SIMPLIFIED_CHINESE;
    private static ZoneId zone = ZoneId.systemDefault();
    private static ZoneOffset offset = ZoneOffset.of("+8");

    /**
     * 获取当前时间（年月日）
     *
     * @return
     */
    public static LocalDate getLocalDateNow() {
        return LocalDate.now(zone);
    }

    /**
     * 获取当前之间年月日时分秒
     *
     * @return
     */
    public static LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now(zone);
    }

    /**
     * 获取当前时间时分秒
     *
     * @return
     */
    public static LocalTime getLocalTimeNow() {
        return LocalTime.now(zone);
    }

    //时间计算

    public static String formatString(String pattern) {
        return getLocalDateTimeNow().format(DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 计算时间加法
     *
     * @return
     * @input ChronoUnit 年月日周
     * @input Count 个数
     */
    public static LocalDate LocalDateplus(LocalDate localDate, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {
            case DAYS:
                return localDate.plusDays(Count);
            case YEARS:
                return localDate.plusYears(Count);
            case MONTHS:
                return localDate.plusMonths(Count);
            case WEEKS:
                return localDate.plusWeeks(Count);
            default:
                return getLocalDateNow();
        }
    }

    /**
     * 计算时间减法
     *
     * @return
     * @input ChronoUnit 年月日周
     * @input Count 个数
     */
    public static LocalDate LocalDateMinus(LocalDate localDate, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {
            case DAYS:
                return localDate.minusDays(Count);
            case YEARS:
                return localDate.minusYears(Count);
            case MONTHS:
                return localDate.minusMonths(Count);
            case WEEKS:
                return localDate.minusWeeks(Count);
            default:
                return getLocalDateNow();
        }
    }

    /**
     * 计算施加加法
     *
     * @param localTime  时间
     * @param chronoUnit 时分秒纳秒
     * @param Count
     * @return
     */
    public static LocalTime LocalTimePlus(LocalTime localTime, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {

            case HOURS:
                return localTime.plusHours(Count);
            case MINUTES:
                return localTime.plusMinutes(Count);
            case SECONDS:
                return localTime.plusSeconds(Count);
            case NANOS:
                return localTime.plusNanos(Count);
            default:
                return getLocalTimeNow();
        }
    }

    /**
     * 计算时间减法
     *
     * @param localTime  时间
     * @param chronoUnit 时分秒纳秒
     * @param Count
     * @return
     */
    public static LocalTime LocalTimeMinus(LocalTime localTime, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {
            case HOURS:
                return localTime.minusHours(Count);
            case MINUTES:
                return localTime.minusMinutes(Count);
            case SECONDS:
                return localTime.minusSeconds(Count);
            case NANOS:
                return localTime.minusNanos(Count);
            default:
                return getLocalTimeNow();
        }
    }


    /**
     * 计算施加加法
     *
     * @param localDateTime 时间
     * @param chronoUnit    年月日时分秒纳秒
     * @param Count
     * @return
     */
    public static LocalDateTime LocalDateTimePlus(LocalDateTime localDateTime, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {
            case DAYS:
                return localDateTime.plusDays(Count);
            case YEARS:
                return localDateTime.plusYears(Count);
            case MONTHS:
                return localDateTime.plusMonths(Count);
            case WEEKS:
                return localDateTime.plusWeeks(Count);
            case HOURS:
                return localDateTime.plusHours(Count);
            case MINUTES:
                return localDateTime.plusMinutes(Count);
            case SECONDS:
                return localDateTime.plusSeconds(Count);
            case NANOS:
                return localDateTime.plusNanos(Count);
            default:
                return getLocalDateTimeNow();
        }
    }

    /**
     * 计算时间加法
     *
     * @param localDateTime
     * @param day
     * @param years
     * @param months
     * @param hours
     * @param minutes
     * @param seconds
     * @param nanos
     * @return
     */
    public static LocalDateTime localDateTimePlus(LocalDateTime localDateTime, int day, int years, int months, int hours, int minutes, int seconds, int nanos) {
        return localDateTime.plusYears(years).plusMonths(months).plusDays(day).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds).plusNanos(nanos);
    }

    /**
     * 计算时间减法
     *
     * @param localDateTime
     * @param day
     * @param years
     * @param months
     * @param hours
     * @param minutes
     * @param seconds
     * @param nanos
     * @return
     */
    public static LocalDateTime localDateTimeMinus(LocalDateTime localDateTime, int day, int years, int months, int hours, int minutes, int seconds, int nanos) {
        return localDateTime.minusYears(years).minusMonths(months).minusDays(day).minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).minusNanos(nanos);
    }

    /**
     * 计算时间减法
     *
     * @param localDateTime 时间
     * @param chronoUnit    年月日时分秒纳秒
     * @param Count
     * @return
     */
    public static LocalDateTime LocalDateTimeMinus(LocalDateTime localDateTime, ChronoUnit chronoUnit, int Count) {
        switch (chronoUnit) {
            case DAYS:
                return localDateTime.minusDays(Count);
            case YEARS:
                return localDateTime.minusYears(Count);
            case MONTHS:
                return localDateTime.minusMonths(Count);
            case WEEKS:
                return localDateTime.minusWeeks(Count);
            case HOURS:
                return localDateTime.minusHours(Count);
            case MINUTES:
                return localDateTime.minusMinutes(Count);
            case SECONDS:
                return localDateTime.minusSeconds(Count);
            case NANOS:
                return localDateTime.minusNanos(Count);
            default:
                return getLocalDateTimeNow();
        }
    }

    /**
     * 通过字符串格式化出时间
     *
     * @param date    日期
     * @param pattern g格式化字符串
     * @return
     */
    public static LocalTime getDateTime(String date, String pattern) {
        return LocalTime.parse(date, DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 通过字符串格式化出时间
     *
     * @param date    日期
     * @param pattern g格式化字符串
     * @return
     */
    public static LocalDate getLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern, locale));
    }


    /**
     * 通过字符串格式化出时间
     *
     * @param date    日期
     * @param pattern g格式化字符串
     * @return
     */
    public static LocalDateTime getDateLocalTime(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 日期格式化成日期字符串
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 日期格式化成日期字符串
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatString(LocalTime localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern, locale));
    }

    /**
     * 日期格式化成日期字符串
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String formatString(LocalDateTime localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern, locale));
    }


    public static String getNowTimeBySecond() {
        //获取秒数
        Long second = getLocalDateTimeNow().toEpochSecond(offset);
        return second.toString();
    }


    public static String getNowTimeBymilliSecond() {
        //获取毫秒数
        Long milliSecond = getLocalDateTimeNow().toInstant(offset).toEpochMilli();
        return milliSecond.toString();
    }


}
