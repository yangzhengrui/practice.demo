package com.practice.datetime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * Java 8引入了新的日期和时间API，它们是不变类，默认按ISO 8601标准格式化和解析；
 * 使用LocalDateTime可以非常方便地对日期和时间进行加减，或者调整日期和时间，它总是返回新对象；
 * 使用isBefore()和isAfter()可以判断日期和时间的先后；
 * 使用Duration和Period可以表示两个日期和时间的“区间间隔”。
 */
public class LocalDateTimeTest {
    /**
     * 注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
     * 
     * 日期：yyyy-MM-dd 时间：HH:mm:ss 带毫秒的时间：HH:mm:ss.SSS 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    @Test
    public void LocalDateTimeTest() {
        // LocalDate d=LocalDate.now();//当前日期
        // LocalTime t=LocalTime.now();//当前时间
        LocalDateTime dt = LocalDateTime.now();// 当前日期和时间
        LocalDate d = dt.toLocalDate();
        LocalTime t = dt.toLocalTime();
        System.out.println(d);// 严格按照ISO 8601格式打印
        System.out.println(t);// 严格按照ISO 8601格式打印
        System.out.println(dt);// 严格按照ISO 8601格式打印

        LocalDate d2 = LocalDate.of(2019, 10, 30);
        LocalTime t2 = LocalTime.of(15, 16, 17);
        LocalDateTime dt2 = LocalDateTime.of(2019, 10, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);

        LocalDateTime dt4 = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate d3 = LocalDate.parse("2019-11-19");
        LocalTime t3 = LocalTime.parse("15:16:17");
    }

    @Test
    public void DateTimeFormatterTest() {
        // 自定义格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        // 用自定义格式解析
        LocalDateTime dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);
    }

    @Test
    public void LocalDateTimeCalcTest() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 加5天减3小时:
        LocalDateTime dt2 = dt.plusDays(5).minusHours(3);
        System.out.println(dt2); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt2.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59
        // 日期变为31日:
        LocalDateTime dt4 = dt.withDayOfMonth(31);
        System.out.println(dt2); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime dt5 = dt4.withMonth(9);
        System.out.println(dt3); // 2019-09-30T20:30:59

        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第1个工作日:
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);
    }

    @Test
    public void LocalDateTimeCompareTest() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
    }

    /**
     * Duration表示两个时刻之间的时间间隔。另一个类似的Period表示两个日期之间的天数. Duration和Period的表示方法也符合ISO
     * 8601的格式，它以P...T...的形式表示，P...T之间表示日期间隔，T后面表示时间间隔。
     * 如果是PT...的格式表示仅有时间间隔。利用ofXxx()或者parse()方法也可以直接创建Duration：
     * 
     * 有的童鞋可能发现Java 8引入的java.timeAPI。怎么和一个开源的Joda Time很像？难道JDK也开始抄袭开源了？
     * 其实正是因为开源的Joda Time设计很好，应用广泛，所以JDK团队邀请Joda Time的作者Stephen
     * Colebourne共同设计了java.timeAPI。
     */
    @Test
    public void DurationAndPeriodTest() {
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d = Duration.between(start, end);
        System.out.println(d); // PT1235H10M30S

        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D

        //利用ofXxx()或者parse()方法也可以直接创建Duration：
        Duration d1 = Duration.ofHours(10); // 10 hours
        Duration d2 = Duration.parse("P1DT2H3M"); // 1 day, 2 hours, 3 minutes
    }
}