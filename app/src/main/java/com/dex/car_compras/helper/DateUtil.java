package com.dex.car_compras.helper;

import java.text.SimpleDateFormat;

public class DateUtil {
    public static String dateNow() {
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String dateString = simpleDateFormat.format(date);

        return dateString;
    }

    public static String dateSaveFormat(String d) {
        String returnDate[] = d.split("/");
        String monthYear = returnDate[1] + returnDate[2];

        return monthYear;
    }
}
