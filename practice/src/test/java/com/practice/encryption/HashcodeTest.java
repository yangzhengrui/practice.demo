package com.practice.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * 哈希算法（Hash）又称摘要算法（Digest），它的作用是：对任意一组输入数据进行计算，得到一个固定长度的输出摘要。 相同的输入一定得到相同的输出；
 * 不同的输入大概率得到不同的输出
 */
public class HashcodeTest {
    @Test
    public void MessageDigestTest() throws Exception {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update("Hello".getBytes("UTF-8"));
        md.update("World".getBytes("UTF-8"));
        
    }
}