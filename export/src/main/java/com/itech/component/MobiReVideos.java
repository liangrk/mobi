package com.itech.component;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.core.Reflection;
import com.itech.export.MobiReVideoListener;
import com.itech.export.MobiReward;

import java.util.Set;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/02 17:15
 *     @desc   : reward video.
 * </pre>
 */
public class MobiReVideos {

    private Class<?> videoUtils;

    public MobiReVideos() {
        videoUtils = PluginManager.getClass(RConstants.CLA_MOBIVIDEOS);
    }

    public void showVideo(@NonNull String unitId) {
        try {
            new Reflection.MethodBuilder(null, "showVideo")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(String.class, unitId)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoaded(@NonNull String unitId) {
        try {
            return (boolean) new Reflection.MethodBuilder(null,"hasVideo")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(String.class,unitId)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadVideo(@NonNull Activity activity, String unitId) {
        try {
            new Reflection.MethodBuilder(null, "supportLoad")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(Activity.class, activity)
                    .addParam(String.class, unitId)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void loadVideo(@Nullable MediationSettings... settings) {
//        try {
//            new Reflection.MethodBuilder(null, "loadVideo")
//                    .setStatic(videoUtils)
//                    .setAccessible()
//                    .addParam(String.class, unitId)
//                    .addParam(MediationSettings[].class, settings)
//                    .execute();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public Set<MobiReward> getAvailableRewards(@NonNull String unitId) {
        try {
            Object set = new Reflection.MethodBuilder(null, "getAvailableRewards")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(String.class, unitId)
                    .execute();
            return (Set<MobiReward>) set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onDestroy() {
        setReVideoListener(null);
    }

    public void setReVideoListener(MobiReVideoListener listener) {
        try {
            new Reflection.MethodBuilder(null, "setReVideoListener")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(MobiReVideoListener.class, listener)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
