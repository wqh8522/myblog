package com.wqh.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanqh
 * @date 2017/11/26 13:29
 * @description: 数据验证工具类
 */
public class MatcherUtil {

    private static final String EMAIL_REG ="\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b";

    public static Boolean matcherEmail(String email){
        return Pattern.matches(EMAIL_REG,email);
    }

   /* public static void main(String[] args) {
        Boolean aBoolean = MatcherUtil.matcherEmail("123@123.");
        System.out.println(aBoolean);
    }*/
}
