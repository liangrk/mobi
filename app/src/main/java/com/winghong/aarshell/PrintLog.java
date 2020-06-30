package com.winghong.aarshell;

import android.util.Log;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 10:33
 *     @desc   :
 * </pre>
 */
public class PrintLog {

    private static String TAG = "CUSTOM_PRINT_LOG";

    public static void toLog(String msg) {
        Log.w(TAG, msg);
    }
}
