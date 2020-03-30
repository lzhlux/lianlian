package com.lianlian.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 数据库密码加密工具类
 */
public class DataSourcePasswordUtils {

    /**
     * 加密
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String encrypt(String cipherText) throws Exception {
        String encrypt = ConfigTools.encrypt(cipherText);
        return encrypt;
    }

    /**
     * 解密
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherText) throws Exception {
        String decrypt = ConfigTools.decrypt(cipherText);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String[] a = new String[]{"ddddd"
        };
        ConfigTools.main(a);
        String password = "blm!@#2019**";
        String encrypt = encrypt(password);
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);

    }
}