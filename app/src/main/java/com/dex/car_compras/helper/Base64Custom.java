package com.dex.car_compras.helper;

import android.util.Base64;

public class Base64Custom {
    public static String condifyBase64(String s) {
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT)
                .replaceAll("\\n|\\r", "");
    }

    public static String decodifyBase64(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT));
    }
}
