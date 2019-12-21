package com.practice.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

/**
 * Instant表示高精度时间戳，它可以和ZonedDateTime以及long互相转换。
 */
public class InstantTest {
    /**
     * 当前时间戳在java.time中以Instant类型表示，我们用Instant.now()获取当前时间戳，效果和System.currentTimeMillis()类似
     */
    @Test
    public void InstantTest() {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond()); // 秒
        System.out.println(now.toEpochMilli()); // 毫秒

        // 以指定时间戳创建Instant:
        Instant ins = Instant.ofEpochSecond(1568568760);
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
    }
    /**
     *  ┌─────────────┐
        │LocalDateTime│────┐
        └─────────────┘    │    ┌─────────────┐
                           ├───>│ZonedDateTime│
        ┌─────────────┐    │    └─────────────┘
        │   ZoneId    │────┘           ▲
        └─────────────┘      ┌─────────┴─────────┐
                             │                   │
                             ▼                   ▼
                    ┌─────────────┐     ┌─────────────┐
                    │   Instant   │<───>│    long     │
                    └─────────────┘     └─────────────┘
     */
}