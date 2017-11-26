package com.wqh.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author wqh
 * @Date 2017/10/20 16:18
 * @Description:
 */
public  class Constants {


    public static final String CLAIMS = "claims";
    /**删除*/
    public static final Integer IS_DEL = 1;
    /**未删除*/
    public static final Integer IS_NOT_DEL = 0;

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getID(){
        String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String timeInMillis = String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(6);
        return date+ timeInMillis;
    }

    /*public static void main(String[] args) {
        String id = Constants.getID();
        System.out.println(id);
    }*/

}
