package com.gongfu.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * 2017年1月13日
 *
 * @向治家 yhqb
 * CryptUtils.java
 * 加解密相关工具类
 **/
@Slf4j
public class CryptUtils {
    /**
     * 密钥
     */
    private static final String privateKey = "Crackers and the thief will suffer misfortune";

    /**
     * 可逆加密
     *
     * @param source 需要加密的内容
     * @return
     */
    public static String encrypt(final String source) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(privateKey.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = source.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String e = Base64.encodeBase64String(result);
            return e; // 加密
        } catch (Exception e) {
            log.error("encrypt error!", e);
        }
        return null;
    }

    /**
     * 可逆解密
     *
     * @param encryptedStr
     * @return
     */
    public static String decrypt(final String encryptedStr) {
        try {
            byte[] content = Base64.decodeBase64(encryptedStr);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(privateKey.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            String r = new String(result, "UTF-8");
            return r; // 解密
        } catch (Exception e) {
            log.error("decrypt error!", e);
        }
        return null;
    }

    /**
     * 用于生成密码盐
     *
     * @return
     */
    public static String generateToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static String sha256Hex(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public static String md5Hex(String str) {
        return DigestUtils.md5Hex(str);
    }
}
