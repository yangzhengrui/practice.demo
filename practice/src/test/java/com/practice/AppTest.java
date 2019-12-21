package com.practice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws UnsupportedEncodingException
     */
    @Test
    public void shouldAnswerWithTrue() throws UnsupportedEncodingException
    {
        System.out.println(URLEncoder.encode("2019年11月","GBK"));
        System.out.println(URLDecoder.decode("2019%e5%b9%b406%e6%9c%88%2c4172*****%40qq.com%2cQqv3fG","utf-8"));
        assertTrue( true );
    }
}
