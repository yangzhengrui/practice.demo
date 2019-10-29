package com.practice.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

/**
 * 处理日期和时间时，尽量使用新的java.time包；
 * 在数据库中存储时间戳时，尽量使用long型时间戳，它具有省空间，效率高，不依赖数据库的优点。
 */
public class BestPracticesTest {

    /**
     * 旧API转新API
     */
    @Test
    public void OldApiToNewApiTest() {
        // Date -> Instant:
        Instant ins1 = new Date().toInstant();

        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant ins2 = Calendar.getInstance().toInstant();
        ZonedDateTime zdt = ins2.atZone(calendar.getTimeZone().toZoneId());
    }

    /**
     * 新API转旧API
     */
    @Test
    public void NewApiToOldApiTest() {
        // ZonedDateTime -> long:
        ZonedDateTime zdt = ZonedDateTime.now();
        long ts = zdt.toEpochSecond() * 1000;

        // long -> Date:
        Date date = new Date(ts);

        // long -> Calendar:
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
        calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);
    }

    @Test
    public void SqlDateTest() {
        long ts = 1574208900000L;
        System.out.println(timestampToString(ts, Locale.CHINA, "Asia/Shanghai"));
        System.out.println(timestampToString(ts, Locale.US, "America/New_York"));
    }
    String timestampToString(long epochMilli, Locale lo, String zoneId) {
        Instant ins = Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
    }
}