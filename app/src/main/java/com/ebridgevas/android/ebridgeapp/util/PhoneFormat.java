package com.ebridgevas.android.ebridgeapp.util;

public class PhoneFormat {

    public static String stripExceptNumbers(String str) {
        return stripExceptNumbers(str, false);
    }

    public static String stripExceptNumbers(String str, boolean includePlus) {
        StringBuilder res = new StringBuilder(str);
        String phoneChars = "0123456789";
        if (includePlus) {
            phoneChars += "+";
        }
        for (int i = res.length() - 1; i >= 0; i--) {
            if (!phoneChars.contains(res.substring(i, i + 1))) {
                res.deleteCharAt(i);
            }
        }
        return res.toString();
    }
}
