package com.practice.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

/**
 * 计算机表示的时间是以整数表示的时间戳存储的，即Epoch Time，Java使用long型来表示以毫秒为单位的时间戳，通过System.currentTimeMillis()获取当前时间戳。
 * Java有两套日期和时间的API：
 *  旧的Date、Calendar和TimeZone；
    新的LocalDateTime、ZonedDateTime、ZoneId等。
    分别位于java.util和java.time包中。
 */
public class DatetimeTest {

    /**
     * Date对象有几个严重的问题：它不能转换时区,也很难对日期和时间进行加减
     */
    @Test
    public void DateTimeApiTest() {
        Date date = new Date();// 获取当前日期
        System.out.println(date.getYear() + 1900);// 必须加上1900
        System.out.println(date.getMonth() + 1);// 0~11 必须加1
        System.out.println(date.getDate());// 1~31 不能加1

        System.out.println(date.getDay());
        System.out.println(date.toString());// 转换为String
        System.out.println(date.toGMTString());// 转换为GMT时区
        System.out.println(date.toLocaleString());// 转换为本地时区
    }

    /**
     * SimpleDateFormat yyyy：年 MM：月 dd: 日 HH: 小时 mm: 分钟 ss: 秒
     */
    @Test
    public void SimpleDateFormatTest() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        SimpleDateFormat sdf1 = new SimpleDateFormat("E MMM dd, yyyy");// 字母越长，输出越长
        // M：输出9
        // MM：输出09
        // MMM：输出Sep
        // MMMM：输出September
        System.out.println(sdf1.format(date));
    }

    @Test
    public void CalendarTest() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = 1 + calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int w = calendar.get(Calendar.DAY_OF_WEEK);//1~7分别表示周日，周一，……，周六。
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);

        calendar.clear();//清除所有
        calendar.set(Calendar.YEAR,2019);//设置年份
        calendar.set(Calendar.MONTH, 9);//设置月份 ：9 代表10月
        calendar.set(Calendar.DATE, 30);//设置日
        calendar.set(Calendar.HOUR_OF_DAY, 21);//设置时间
        calendar.set(Calendar.MINUTE,22);
        calendar.set(Calendar.SECOND, 23);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
    }

    @Test
    public void TimeZoneTest() {
        TimeZone tzDefault=TimeZone.getDefault();//当前时区
        TimeZone tzGMT9=TimeZone.getTimeZone("GMT+09:00");//GMT+9:00时区
        TimeZone tzNY=TimeZone.getTimeZone("America/New_York");//纽约时区
        System.out.println(tzDefault.getID());//Asia/Shanghai
        System.out.println(tzGMT9.getID());//GMT+09:00
        System.out.println(tzNY.getID());//America/New_York

        Calendar calendar=Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        calendar.set(2019, 9/*10月 */, 20,8,15,0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf.format(calendar.getTime()));
    }

}
