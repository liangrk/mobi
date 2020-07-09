package com.itech.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 18:58
 *     @desc   :
 * </pre>
 */
public class SPHelper {

    private static SharedPreferences getSharedPreferences() {
        return AppUtils.getApplication().getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    public static boolean putString(String key, String value) {
        return getSharedPreferences().edit()
                .putString(key, value)
                .commit();
    }

    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }
}
