package com.dex.car_compras.helper;

import java.text.SimpleDateFormat;

public class DateUtil {
    public static String dateNow(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/YYYY hh:mm:ss");
        String dateString = simpleDateFormat.format(date);

        return dateString;
    }
}
