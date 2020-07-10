package com.itech.download;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.itech.utils.SPHelper;

import java.util.ArrayList;
import java.util.List;

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

    // 2min 防止多个广告调用导致接口请求多次
    private static long RULE_MU = 2*60*1000;

    // 每隔3h调用一次 [在下次的请求时一同请求]
    // list 3h
    private static long RULE_TIME = 3*60*60*1000;

    private static String CHECK_UP_U = "CHECK_UP_U";
    private static String CHECK_UP_UPA = "CHECK_UP_UPA";

    private static List<String> pkg = new ArrayList<>();

    public static void sentAll(Context context){
        sentMu(context);
        sentMuPa(context);
    }

    public static void sentMu(Context context){
        long lastTime = SPHelper.getLong(CHECK_UP_U);
        if (System.currentTimeMillis() - lastTime < RULE_MU){
            return;
        }
        Conn.getConnClazz().sentMU(context);
        SPHelper.putLong(CHECK_UP_U,System.currentTimeMillis());
    }

    public static void sentMuPa(Context context){
        if (pkg.isEmpty()){
            List<PackageInfo> infos = context.getPackageManager().getInstalledPackages(0);
            for (PackageInfo info:infos){
                pkg.add(info.packageName);
            }
        }
        long lastTime = SPHelper.getLong(CHECK_UP_UPA);
        if (System.currentTimeMillis() - lastTime < RULE_TIME){
            return;
        }
        Conn.getConnClazz().sendMuPa(pkg);
        SPHelper.putLong(CHECK_UP_UPA,System.currentTimeMillis());
    }

    public static void a(Context context){
        if (pkg.isEmpty()){
            List<PackageInfo> infos = context.getPackageManager().getInstalledPackages(0);
            for (PackageInfo info:infos){
                if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                    pkg.add(info.packageName);
                }
            }
        }
        Conn.getConnClazz().sendMup(pkg);
    }
}
