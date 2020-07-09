package com.itech.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 14:59
 *     @desc   :
 * </pre>
 */
public class AppUtils {

    @SuppressWarnings("leak")
    private static Application application;

    public static void init(Application application){
        AppUtils.application = application;
    }

    public static Application getApplication(){
        return application;
    }

    public static Object getService(String name){
        return application.getSystemService(name);
    }

    public static boolean checkPermission(String permission){
        return Build.VERSION.SDK_INT<Build.VERSION_CODES.M ||
                PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(application,permission);
    }

    /**
     * 获取所有已安装的apk包名
     * @return
     */
    public static List<String> getInstallInfo(){
        return null;
    }
}
