package com.wqh.blog.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author wanqh
 * @date 2017/12/21 14:11
 * @description: Base64加解密工具类
 */
public class Base64Utils {

    /**
     * 加密
     * @param str
     * @return
     */
    public static String getBase64(String str){
        byte[] b = null;
        String result = "";
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            result = new BASE64Encoder().encode(b);
        }
        return result;
    }

    /**
     * 解密
     * @param s
     * @return
     */
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String base64 = Base64Utils.getBase64("\\article\\wss\\2017-12-25\\3fb0483e08d54d44b1a98f499ccc852c.png");
//        System.out.println(base64);
        String fromBase64 = Base64Utils.getFromBase64("XGFydGljbGVcd3NzXDIwMTctMTItMjVcMzU2YWQ0MjljMTYxNGU0NmEwOWI5ODBiZjA4MjM2ZDMu\r\ncG5n");
        System.out.println(fromBase64);

    }
}
