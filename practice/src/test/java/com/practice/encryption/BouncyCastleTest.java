package com.practice.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * BouncyCastle是一个开源的第三方算法提供商；
 * BouncyCastle提供了很多Java标准库没有提供的哈希算法和加密算法；
 * 使用第三方算法前需要通过Security.addProvider()注册。
 */
public class BouncyCastleTest {

    @BeforeEach
    public void InitSource() {
        // 注册BouncyCastle
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    public void RipeMD160Test() throws Exception {

        // MessageDigest md=MessageDigest.getInstance("RipeMD160");
        // md.update("HelloWorld".getBytes("UTF-8"));
        // byte[] result=md.digest();
        // System.out.println(new BigInteger(1,result).toString(16));
        
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        md.update("HelloWorld".getBytes("UTF-8"));
        byte[] result = md.digest();
        System.out.println(new BigInteger(1, result).toString(16));
    }
}