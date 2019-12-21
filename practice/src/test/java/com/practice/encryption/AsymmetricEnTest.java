package com.practice.encryption;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.junit.jupiter.api.Test;

/**
 * 非对称加密的典型算法就是RSA算法
 * 非对称加密就是加密和解密使用的不是相同的密钥，只有同一个公钥-私钥对才能正常加解密；
 * 只使用非对称加密算法不能防止中间人攻击。
 */
public class AsymmetricEnTest {

    @Test
    public void RSATest() throws Exception {
        // 明文:
        byte[] plain = "Hello, encrypt use RSA".getBytes("UTF-8");
        // 创建公钥／私钥对:
        Person alice = new Person("Alice");
        // 用Alice的公钥加密:
        byte[] pk = alice.getPublicKey();
        System.out.println(String.format("public key: %x", new BigInteger(1, pk)));
        byte[] encrypted = alice.encrypt(plain);
        System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
        // 用Alice的私钥解密:
        byte[] sk = alice.getPrivateKey();
        System.out.println(String.format("private key: %x", new BigInteger(1, sk)));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
    }
    class Person {
        String name;
        // 私钥:
        PrivateKey sk;
        // 公钥:
        PublicKey pk;
    
        public Person(String name) throws GeneralSecurityException {
            this.name = name;
            // 生成公钥／私钥对:
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
            kpGen.initialize(1024);
            KeyPair kp = kpGen.generateKeyPair();
            this.sk = kp.getPrivate();
            this.pk = kp.getPublic();
        }
    
        // 把私钥导出为字节
        public byte[] getPrivateKey() {
            return this.sk.getEncoded();
        }
    
        // 把公钥导出为字节
        public byte[] getPublicKey() {
            return this.pk.getEncoded();
        }
    
        // 用公钥加密:
        public byte[] encrypt(byte[] message) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.pk);
            return cipher.doFinal(message);
        }
    
        // 用私钥解密:
        public byte[] decrypt(byte[] input) throws GeneralSecurityException {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.sk);
            return cipher.doFinal(input);
        }
    }
}