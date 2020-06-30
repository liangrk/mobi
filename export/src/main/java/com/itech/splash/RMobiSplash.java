package com.itech.splash;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.export.MobiSplashListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 18:01
 *     @desc   :
 * </pre>
 */
public class RMobiSplash {

    private Context context;
    private ClassLoader classLoader;
    private Object splash;
    private Class<?> splashClass;

    public RMobiSplash(@NonNull Context context) {
        try {
            classLoader = PluginManager.getInstance().getClassLoader();
            splashClass = classLoader.loadClass(RConstants.CLA_SPLASH);
            splash = splashClass.getConstructor(Context.class)
                    .newInstance(context);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void loadSplash(@NonNull String unitId,
                           @NonNull MobiSplashListener listener,
                           @NonNull int timeout) {
        try {
            splashClass = classLoader.loadClass(RConstants.CLA_SPLASH);
            Method[] methods = splashClass.getMethods();
//            for (int i = 0; i < ; i++) {
//
//            }
            Method loadSplashMet = splashClass.getMethod("loadSplash",
                    String.class, MobiSplashListener.class, int.class);
            loadSplashMet.invoke(splash,unitId,listener,timeout);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
