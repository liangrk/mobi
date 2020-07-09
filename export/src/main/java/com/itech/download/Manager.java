package com.itech.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.core.Reflection;
import com.itech.utils.AppUtils;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 15:29
 *     @desc   :
 * </pre>
 */
public class Manager {
    public static int NV = 1;
    public static int PV = 1;

    public static String getAndroidId() {
        return Settings.System.getString(AppUtils.getApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @SuppressLint("HardwareIds")
    public static String getAndroidIme() {
        String ime = null;
        try {
            TelephonyManager manager = (TelephonyManager) AppUtils.getService(Context.TELEPHONY_SERVICE);
            if (AppUtils.checkPermission("android.permission.READ_PHONE_STATE")) {
                if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
                    ime = manager.getImei();
                }
                return ime != null ? ime : manager.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMid() {
        Class playService = PluginManager.getInstance().getClass(RConstants.CLA_PLAY_SERVICE);
        try {
            return (String) new Reflection.MethodBuilder(null,"initMid")
                    .setAccessible()
                    .setStatic(playService)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
