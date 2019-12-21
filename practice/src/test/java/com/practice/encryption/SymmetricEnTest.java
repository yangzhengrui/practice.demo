package com.practice.encryption;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;

/**
 * 对称加密算法使用同一个密钥进行加密和解密，常用算法有DES、AES和IDEA等；
 * 密钥长度由算法设计决定，AES的密钥长度是128/192/256位；
 * 使用对称加密算法需要指定算法名称、工作模式和填充模式。
 */
public class SymmetricEnTest {

    @Test
    public void AESEncrypt() throws Exception {
        // 原文
        String message = "Hello,World!";
        System.out.println("Message:" + message);
        // 128位密钥 = 16 bytes Key:
        byte[] key = "1234567890abcdef".getBytes("UTF-8");
        // 加密：
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted:" + Base64.getEncoder().encodeToString(encrypted));
        // 解密：
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted:" + new String(decrypted, "UTF-8"));
    }

    @Test
    public void AES_CBC_Encrypt() throws Exception {
        // 原文
        String message = "Hello,World!";
        System.out.println("Message:" + message);
        // 256位密钥 = 32 bytes Key:
        byte[] key = "1234567890abcdef1234567890abcdef".getBytes("UTF-8");
        // 加密：
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt_CBC(key, data);
        System.out.println("Encrypted:" + Base64.getEncoder().encodeToString(encrypted));
        // 解密：
        byte[] decrypted = decrypt_CBC(key, encrypted);
        System.out.println("Decrypted:" + new String(decrypted, "UTF-8"));
    }

    private byte[] decrypt_CBC(byte[] key, byte[] input) throws GeneralSecurityException{
        // 把input分割成IV和密文:
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, 16);
        System.arraycopy(input, 16, data, 0, data.length);
        // 解密:
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(data);
    }

    private byte[] encrypt_CBC(byte[] key, byte[] data) throws GeneralSecurityException {
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey keySpec=new SecretKeySpec(key,"AES");
        // CBC模式需要生成一个16 bytes的initialization vector:
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivps); 
        byte[] rdata = cipher.doFinal(data);
        // IV不需要保密，把IV和密文一起返回:
        return join(iv, rdata);
    }

    private byte[] join(byte[] bs1, byte[] bs2) {
        byte[] r = new byte[bs1.length + bs2.length];
        System.arraycopy(bs1, 0, r, 0, bs1.length);
        System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
        return r;
    }

    /**
     * 解密
     * 
     * @param key
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    private byte[] decrypt(byte[] key, byte[] data) throws GeneralSecurityException {
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec=new SecretKeySpec(key,"AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * @param key
     * @param data
     * @return
     */
    private byte[] encrypt(byte[] key, byte[] data) throws GeneralSecurityException  {
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec=new SecretKeySpec(key,"AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }
}
