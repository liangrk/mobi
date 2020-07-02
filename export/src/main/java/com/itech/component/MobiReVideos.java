package com.itech.component;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.core.Reflection;
import com.itech.export.MediationSettings;
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

    private String unitId;
    private Class<?> videoUtils;
    private Object set;

    public MobiReVideos(@NonNull String unitId) {
        this.unitId = unitId;
        try {
            videoUtils = PluginManager.getInstance().getClassLoader().loadClass(RConstants.CLA_MOBIVIDEOS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showVideo() {
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

    public boolean isLoaded() {
        try {
            new Reflection.MethodBuilder(null, "hasVideo")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(String.class, unitId)
                    .execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadVideo() {
        loadVideo(null);
    }

    public void loadVideo(@Nullable MediationSettings... settings) {
        try {
            new Reflection.MethodBuilder(null, "loadVideo")
                    .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(String.class, unitId)
                    .addParam(MediationSettings[].class, settings)
                    .execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<MobiReward> getAvailableRewards() {
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

//    public void loadVideo(@NonNull Activity activity,){
//
//    }

    public void setReVideoListener(MobiReVideoListener listener) {
        try {
            new Reflection.MethodBuilder(null,"setReVideoListener")
                .setStatic(videoUtils)
                    .setAccessible()
                    .addParam(MobiReVideoListener.class,listener)
                    .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
