package com.qldt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public static String convertStr(Date date){
        return format.format(date);
    }
    public static Date converDate(String date){
        try {
            return format.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
