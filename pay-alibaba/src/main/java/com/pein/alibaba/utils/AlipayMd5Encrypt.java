package com.pein.alibaba.utils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * 功能：支付宝MD5签名处理核心文件，不需要修改
 * 版本：3.1
 * 修改日期：2010-11-01
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 * */

public class AlipayMd5Encrypt {

    /**
     * 对字符串进行MD5签名
     *
     * @param text
     *            明文
     *
     * @return 密文
     */
    public static String md5(String text,String inputCharset) {

        return DigestUtils.md5Hex(getContentBytes(text, inputCharset));

    }


    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param inputCharset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key,String inputCharset) {
        text = text + key;
        System.out.println("text=="+text);
        return DigestUtils.md5Hex(getContentBytes(text, inputCharset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

}
