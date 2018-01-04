package com.wqh.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author wqh
 * @Date 2017/10/20 16:18
 * @Description:
 */
public class Constants {


    public static final String CLAIMS = "claims";
    /**
     * 删除
     */
    public static final Integer IS_DEL = 1;
    /**
     * 未删除
     */
    public static final Integer IS_NOT_DEL = 0;

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取id，格式：时间yyyyMMdd+六位时间戳
     *
     * @return
     */
    public static String getID() {
        String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String timeInMillis = String.valueOf(System.currentTimeMillis()).substring(6);
        return date + timeInMillis;
    }



    public static void main(String[] args) {
//        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
    }

}
