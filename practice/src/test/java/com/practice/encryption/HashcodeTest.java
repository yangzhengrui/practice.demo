package com.practice.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

/**
 * 哈希算法（Hash）又称摘要算法（Digest），它的作用是：对任意一组输入数据进行计算，得到一个固定长度的输出摘要。 相同的输入一定得到相同的输出；
 * 不同的输入大概率得到不同的输出
 * 常用的哈希算法有MD5、SHA-1等；
 */
public class HashcodeTest {
    @Test
    public void MessageDigestTest() throws Exception {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update("Hello".getBytes("UTF-8"));
        md.update("World".getBytes("UTF-8"));
        byte[] result=md.digest();
        System.out.println(new BigInteger(1,result).toString(16));
        
    }
}