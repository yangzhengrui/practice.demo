package com.practice.regex;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest
{
    @Test
    public void RegexMatchTest(){
        String re = "^[0]\\d{2,3}\\-[1-9]\\d{6,7}$";
        for (String s : new String[]{"010-12345678", "020-9999999", "0755-7654321"}) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : new String[]{"010 12345678", "A20-9999999", "0755-7654.321"}) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");

        String re1 = "learn\\s([j,J]ava|[p,P]hp|[g,G]o)";
        System.out.println("learn java".matches(re1));
        System.out.println("learn Java".matches(re1));
        System.out.println("learn php".matches(re1));
        System.out.println("learn Go".matches(re1));
    }

    @Test
    public void MatcherGroupTest(){
        Pattern p=Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher m=p.matcher("010-12345678");
        if (m.matches()) {
            String g1=m.group(1);
            String g2=m.group(2);
            System.out.println(g1);
            System.out.println(g2);
        } else {
            System.out.println("匹配失败");
        }

        Pattern p1 =Pattern.compile("(\\d{2})\\:(\\d{2})\\:(\\d{2})");
        Matcher m1=p1.matcher("23:01:59");
        if (m1.matches()) {
            String g1=m1.group(1);
            String g2=m1.group(2);
            String g3=m1.group(3);
            System.out.println(g1);
            System.out.println(g2);
            System.out.println(g3);
        } else {
            System.out.println("匹配失败");
        }
    }

    @Test
    public void SearchOrReplaceTest(){
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }

        String s1 = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r = s1.replaceAll("\\s+", " ");
        System.out.println(r); // "The quick brown fox jumps over the lazy dog."
    }

    
}