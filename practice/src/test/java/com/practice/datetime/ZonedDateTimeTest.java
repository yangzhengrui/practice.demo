package com.practice.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

/**
 * ZonedDateTime是带时区的日期和时间，可用于时区转换；
 * ZonedDateTime和LocalDateTime可以相互转换。
 */
public class ZonedDateTimeTest
{

    @Test
    public void ZonedDateTimeTest() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zbj);
        System.out.println(zny);
        
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbj1 = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zny1 = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zbj1);
        System.out.println(zny1);

        // 要特别注意，时区转换的时候，由于夏令时的存在，不同的日期转换的结果很可能是不同的。
        // 以中国时区获取当前时间:
        ZonedDateTime zbj2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime zny2 = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbj2);
        System.out.println(zny2);
    }
    /**
     * 某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
     */
    @Test
    public void PracticeHomeWork() {
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        ZonedDateTime zbj = bj.atZone(ZoneId.systemDefault());
        // 转换为纽约时间:
        ZonedDateTime zny = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));  
        return zny.plusHours(h).plusMinutes(m).toLocalDateTime();
    }
}