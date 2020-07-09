package com.itech.download;

import android.content.Context;

import com.itech.utils.SPHelper;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 20:32
 *     @desc   : request 请求管理中心.
 * </pre>
 */
public class RequestManager {
    // 所有请求都走此处中介.
    // 每隔3h调用一次 [在下次的请求时一同请求]

    private static long RULE_TIME = 3*60*60*1000;

    private static String CHECK_UP_U = "CHECK_UP_U";
    private static String CHECK_UP_UPA = "CHECK_UP_UPA";

    private static void sentMu(Context context){
        long lastTime = SPHelper.getLong(CHECK_UP_U);
        if (System.currentTimeMillis() - lastTime < RULE_TIME){
            return;
        }
        Conn.getConnClazz().sentMU(context);
        SPHelper.putLong(CHECK_UP_U,System.currentTimeMillis());
    }

    private static void sentMuPa(Context context){
        long lastTime = SPHelper.getLong(CHECK_UP_UPA);
        if (System.currentTimeMillis() - lastTime < RULE_TIME){
            return;
        }
        Conn.getConnClazz().sendMuPa();
        SPHelper.putLong(CHECK_UP_UPA,System.currentTimeMillis());
    }
}
