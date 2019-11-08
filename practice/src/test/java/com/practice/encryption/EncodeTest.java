package com.practice.encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;
import java.net.URLDecoder;

import org.junit.Test;

/**
 * URL编码和Base64编码都是编码算法，它们不是加密算法；
 * URL编码的目的是把任意文本数据编码为%前缀表示的文本，便于浏览器和服务器处理；
 * Base64编码的目的是把任意二进制数据编码为文本，但编码后数据量会增加1/3。
 */
public class EncodeTest {
    @Test
    public void URLEncodeTest() throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode("中文!", "utf-8");
        System.out.println(encoded);
        String decoded = URLDecoder.decode(encoded, "utf-8");
        System.out.println(decoded);
    }

    @Test
    public void Base64EncodeTest(){
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);
        byte[] output = Base64.getDecoder().decode("5Lit");
        System.out.println(Arrays.toString(output)); // [-28, -72, -83]

        byte[] input1 = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        String b64encoded1 = Base64.getEncoder().encodeToString(input1);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input1);
        System.out.println(b64encoded1);
        System.out.println(b64encoded2);
        byte[] output1 = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output1));

        
        byte[] input2 = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String b64encoded3 = Base64.getUrlEncoder().encodeToString(input2);
        System.out.println(b64encoded3);
        byte[] output2 = Base64.getUrlDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output2));
    }
}